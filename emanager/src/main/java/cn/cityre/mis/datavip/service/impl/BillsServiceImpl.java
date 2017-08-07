package cn.cityre.mis.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.datavip.dao.*;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.entity.CartBills;
import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.datavip.entity.UserPaymentInfoHistory;
import cn.cityre.mis.datavip.service.BillsService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import javafx.scene.control.Pagination;
import org.modelmapper.ModelMapper;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by cityre on 2017/7/31.
 */
@Service
public class BillsServiceImpl implements BillsService {
    @Autowired
    private BillsMapper billsMapper;
    @Autowired
    private CartbillsMapper cartbillsMapper;
    @Autowired
    private DicUserTypeCostMapper dicUserTypeCostMapper;
    @Autowired
    private UserPaymentInfoMapper userPaymentInfoMapper;
    @Autowired
    private UserPaymentInfoHistoryMapper userPaymentInfoHistoryMapper;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PaginationResult<Bills> getBillsListByOthers(QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(), queryParams.getFirstResult(), queryParams.getPageNo());
        PageMyBatis<Bills> pageMyBatis = billsMapper.selectDefaultByPage(pagingCriteria);
        PaginationResult<Bills> paginationResult = PaginationResult.pagination(pageMyBatis, (int) pageMyBatis.getTotal(), pagingCriteria.getPageNumber(), pagingCriteria.getDisplaySize());
        return paginationResult;
    }

    @Override
    public void addInvoice(Bills bills) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        billsMapper.updateInvoice(bills);
    }

    @Override
    public Bills getExistBillsById(Integer id) {
        return billsMapper.selectByPrimaryKey(id);
    }

    /**
     * 开通服务
     * 1.将对应的想订单的时间和状态进行跟新
     * 2.对大订单下的各个小订单进行判断，如果所有的小定单都已经支付，则更新大订单下所有的小定单信息
     * 3.将相关的支付的订单信息放入支付信息表中
     * 4.将支付了的信息放入支付历史中
     *
     * @param bills
     */
    @Override
    public void openService(Bills bills) throws ParseException {

        UserPaymentInfo userPaymentInfo = bills.getUserPaymentInfo();

        bills.setPayUpdateTime(userPaymentInfo.getPayTime());
        billsMapper.updateByBillsId(bills);
//        2015.03.20
//        '更新购物车大订单号信息，判断如果大订单中的所有小订单都已经设置支付成功，则需要更新购物车大订单号的信息
        if (bills.getBigBillCode() != null && bills.getBigBillCode() != "") {
            List<Bills> billsList = billsMapper.selectByBigBillCode(bills.getBigBillCode());
            if (billsList == null || billsList.size() == 0) {
                CartBills cartBills = new CartBills();
                cartBills.setBigBillCode(bills.getBigBillCode());
                cartBills.setPayUpdateTime(bills.getUserPaymentInfo().getPayTime());
                cartbillsMapper.updateByBigBillCode(cartBills);
            }
        }
        //将支付表中的用户信息放到支付历史中
        List<UserPaymentInfo> paymentInfo = userPaymentInfoMapper.selectBySuid(bills.getSuid());
        for (UserPaymentInfo payment : paymentInfo) {
            UserPaymentInfoHistory userPaymentInfoHistory = userPaymentInfoHistoryMapper.selectByBillCode(payment.getBillCode());
            if (userPaymentInfoHistory == null) {
                UserPaymentInfoHistory paymentInfoHistory = modelMapper.map(payment, UserPaymentInfoHistory.class);
                paymentInfoHistory.setId(null);
                userPaymentInfoHistoryMapper.insertSelective(userPaymentInfoHistory);
            }
        }
    //系统自动计算终止时间，并保存到支付信息表中
        UserPaymentInfo userPayment = new UserPaymentInfo();
        String cycle = dicUserTypeCostMapper.selectByUserType(bills.getProductCode()).getCycle();
        String date_time = cycle.substring(0, cycle.length() - 1);
        String dateType = cycle.substring(cycle.length() - 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date = simpleDateFormat.parse(simpleDateFormat.format(date));
        //实例化一个日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (bills.getUserPaymentInfo().getStartTime() == null) {
            userPayment.setStartTime(date);
        }
        //判断买了是月还是年还是天
        if (bills.getUserPaymentInfo().getEndTime() == null) {
            if (dateType.equals("y")) {
                calendar.add(Calendar.YEAR, Integer.parseInt(date_time));
            } else if (dateType.equals("m")) {
                calendar.add(Calendar.MONTH, Integer.parseInt(date_time));
            } else {
                calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(date_time));
            }
            userPayment.setEndTime(calendar.getTime());
        }
        userPayment = modelMapper.map(bills, UserPaymentInfo.class);
        userPayment.setPayType(bills.getWPayType());
        userPayment.setPayAmount(bills.getProductCost());
        userPayment.setAccountName(bills.getWPayUser());
        userPaymentInfoMapper.insertSelective(userPayment);
    }

    /**
     * /new/service.asp
     * @param queryParams
     * @return
     */
    @Override
    public PaginationResult<Bills> getUserInfoList(QueryParams queryParams) {
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(),queryParams.getFirstResult(),queryParams.getPageNo());
        PageMyBatis<Bills> pageMyBatis = billsMapper.selectUserInfoByPage(pagingCriteria);
        PaginationResult<Bills> paginationResult = PaginationResult.pagination(pageMyBatis,(int)pageMyBatis.getTotal(),queryParams.getPageNo(),queryParams.getPageSize());
        return paginationResult;
    }
}


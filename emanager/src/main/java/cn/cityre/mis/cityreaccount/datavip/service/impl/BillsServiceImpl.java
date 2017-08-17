package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.*;
import cn.cityre.mis.cityreaccount.datavip.dto.SearchBillParams;
import cn.cityre.mis.cityreaccount.datavip.entity.Bills;
import cn.cityre.mis.cityreaccount.datavip.entity.CartBills;
import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfoHistory;
import cn.cityre.mis.cityreaccount.datavip.service.BillsService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import org.modelmapper.ModelMapper;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public PaginationResult<Bills> getBillsListByOthers(SearchBillParams searchBillParams) throws ParseException {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = billsMapper.selectTime();
        date = simpleDateFormat.parse(simpleDateFormat.format(date));
        QueryParams queryParams = searchBillParams.getQueryParams();
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(), queryParams.getFirstResult(), queryParams.getPageNo());
        Map<String, Object> map = new HashMap<>();
        map.put("pagingCriteria", pagingCriteria);
        if (searchBillParams.getUid() != null) {
            map.put("uid", searchBillParams.getUid());
        }
        if (searchBillParams.getBillCode() != null) {
            map.put("billCode", searchBillParams.getBillCode());
        }
        if (searchBillParams.getAlipayBillCode() != null) {
            map.put("alipayBillCode", searchBillParams.getAlipayBillCode());
        }
        if (searchBillParams.getBigBillCode() != null) {
            map.put("bigBillCode", searchBillParams.getBigBillCode());
        }
        if (searchBillParams.getInvoiceType() != null&&!searchBillParams.getInvoiceType().equals("null")) {
            map.put("invoiceType", searchBillParams.getInvoiceType());
        }
        if (searchBillParams.getTypeCode() != null&&!searchBillParams.getTypeCode().equals("null")) {
            map.put("typeCode", searchBillParams.getTypeCode());
        }
        if (searchBillParams.getInvoiceNo() != null) {
            map.put("invoiceNo", searchBillParams.getInvoiceNo());
        }
        if (searchBillParams.getPostInvoiceFlag() != null&&!searchBillParams.getPostInvoiceFlag().equals("null")) {
            map.put("postInvoiceFlag", searchBillParams.getPostInvoiceFlag());
        }
        if (searchBillParams.getInvoiceNoFlag() != null&&!searchBillParams.getInvoiceNoFlag().equals("null")) {
            map.put("invoiceNoFlag", searchBillParams.getInvoiceNoFlag());
        }
        if (searchBillParams.getPayFlag() != null&&!searchBillParams.getPayFlag().equals("null")) {
            map.put("payFlag", searchBillParams.getPayFlag());
        }
        if (searchBillParams.getPostTypeCode() != null&&!searchBillParams.getPostTypeCode().equals("null")) {
            map.put("postTypeCode", searchBillParams.getPostTypeCode());
        }
        PageMyBatis<Bills> pageMyBatis = billsMapper.selectDefaultByPage(map);
        //判断服务状态
        for (Bills bills : pageMyBatis) {

            if (bills.getInvoiceTitle() != null && bills.getInvoiceTitle().equals("null")) {
                bills.setInvoiceTitle("");
            }
            if (bills.getTel() != null && bills.getTel().equals("null")) {
                bills.setTel("");
            }
            if (bills.getAddress() != null && bills.getAddress().equals("null")) {
                bills.setAddress("");
            }
            if (bills.getPostUser() != null && bills.getPostUser().equals("null")) {
                bills.setPostUser("");
            }

            if (bills.getEndTime() == null) {
                bills.setServiceState(0);//开通服务
            } else if (bills.getEndTime().before(date)) {
                bills.setServiceState(1);//服务时间结束->已关闭
            } else {
                bills.setServiceState(2);//关闭服务
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.roll(Calendar.DAY_OF_MONTH, -1);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 16, 0, 0);
            Date dateRoll = calendar.getTime();
            if (bills.getPayUpdateTime() == null) {
                bills.setConfirmPassword(false);
            } else {
                if (bills.getPayUpdateTime().before(dateRoll)) {
                    bills.setConfirmPassword(true);
                } else {
                    bills.setConfirmPassword(false);
                }
            }
        }
            PaginationResult<Bills> paginationResult = PaginationResult.pagination(pageMyBatis, (int) pageMyBatis.getTotal(), pagingCriteria.getPageNumber(), pagingCriteria.getDisplaySize());
            DataSourceContextHolder.setDbType("dataSource_core");
            return paginationResult;
        }
        @Override
        public List<Bills> getExportList (SearchBillParams searchBillParams){
            DataSourceContextHolder.setDbType("dataSource_cityreaccount");
            Map<String,Object> map = new HashMap<>();
            if (searchBillParams.getUid() != null) {
                map.put("uid", searchBillParams.getUid());
            }
            if (searchBillParams.getBillCode() != null) {
                map.put("billCode", searchBillParams.getBillCode());
            }
            if (searchBillParams.getAlipayBillCode() != null) {
                map.put("alipayBillCode", searchBillParams.getAlipayBillCode());
            }
            if (searchBillParams.getBigBillCode() != null) {
                map.put("bigBillCode", searchBillParams.getBigBillCode());
            }
            if (searchBillParams.getInvoiceType() != null&&!searchBillParams.getInvoiceType().equals("null")) {
                map.put("invoiceType", searchBillParams.getInvoiceType());
            }
            if (searchBillParams.getTypeCode() != null&&!searchBillParams.getTypeCode().equals("null")) {
                map.put("typeCode", searchBillParams.getTypeCode());
            }
            if (searchBillParams.getInvoiceNo() != null) {
                map.put("invoiceNo", searchBillParams.getInvoiceNo());
            }
            if (searchBillParams.getPostInvoiceFlag() != null&&!searchBillParams.getPostInvoiceFlag().equals("null")) {
                map.put("postInvoiceFlag", searchBillParams.getPostInvoiceFlag());
            }
            if (searchBillParams.getInvoiceNoFlag() != null&&!searchBillParams.getInvoiceNoFlag().equals("null")) {
                map.put("invoiceNoFlag", searchBillParams.getInvoiceNoFlag());
            }
            if (searchBillParams.getPayFlag() != null&&!searchBillParams.getPayFlag().equals("null")) {
                map.put("payFlag", searchBillParams.getPayFlag());
            }
            if (searchBillParams.getPostTypeCode() != null&&!searchBillParams.getPostTypeCode().equals("null")) {
                map.put("postTypeCode", searchBillParams.getPostTypeCode());
            }

            List<Bills> list = billsMapper.selectExportInfo(map);
            DataSourceContextHolder.setDbType("dataSource_core");
            return list;

        }

        @Override
        public List<Bills> getExistList () {
            DataSourceContextHolder.setDbType("dataSource_cityreaccount");
            List<Bills> list = billsMapper.selectAllBills();
            DataSourceContextHolder.setDbType("dataSource_core");
            return list;
        }

        @Override
        public void addInvoice (Bills bills){
            DataSourceContextHolder.setDbType("dataSource_cityreaccount");
            billsMapper.updateInvoice(bills);
            DataSourceContextHolder.setDbType("dataSource_core");
        }

        @Override
        public Bills getExistBillsById (Integer id){
            DataSourceContextHolder.setDbType("dataSource_cityreaccount");
            Bills bills = billsMapper.selectByPrimaryKey(id);
            DataSourceContextHolder.setDbType("dataSource_core");
            return bills;
        }

        @Override
        public Bills getExistBillsByCode (String billCode) throws ParseException {
            DataSourceContextHolder.setDbType("dataSource_cityreaccount");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = billsMapper.selectTime();
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
            Bills bills = billsMapper.selectByCode(billCode);
            if (bills.getUserPaymentInfo() == null) {
                bills.setServiceState(0);//开通服务
            } else if (bills.getUserPaymentInfo().getEndTime().before(date)) {
                bills.setServiceState(1);//服务时间结束->已关闭
            } else {
                bills.setServiceState(2);//关闭服务
            }
            //进行是否输入密码的判断
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.roll(Calendar.DAY_OF_MONTH, 1);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 16, 0, 0);
            Date dateRoll = calendar.getTime();
            if (bills.getPayUpdateTime().before(dateRoll)) {
                bills.setConfirmPassword(true);
            } else {
                bills.setConfirmPassword(false);
            }
            DataSourceContextHolder.setDbType("dataSource_core");
            return bills;
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
        public void openService (Bills bills) throws ParseException {
            DataSourceContextHolder.setDbType("dataSource_cityreaccount");
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
            userPayment = modelMapper.map(bills.getUserPaymentInfo(), UserPaymentInfo.class);
            userPayment.setSuid(bills.getSuid());
            userPayment.setBillCode(bills.getBillCode());
            userPayment.setPayType(bills.getWPayType());
            userPayment.setPayAmount(bills.getProductCost());
            userPayment.setAccountName(bills.getWPayUser());

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
            userPayment = modelMapper.map(bills.getUserPaymentInfo(), UserPaymentInfo.class);
            userPayment.setPayType(bills.getWPayType());
            userPayment.setPayAmount(bills.getProductCost());
            userPayment.setAccountName(bills.getWPayUser());
            userPaymentInfoMapper.insertSelective(userPayment);
            DataSourceContextHolder.setDbType("dataSouce_core");
        }

        /**
         * /new/service.asp
         * @param queryParams
         * @return
         */
//    @Override
//    public PaginationResult<Bills> getUserInfoList(List<SearchField> searchFields,QueryParams queryParams) {
//        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
//        PagingCriteria pagingCriteria = PagingCriteria.createCriteriaWithSearch(queryParams.getFirstResult(),queryParams.getPageSize(),queryParams.getPageNo(),searchFields);
//        PageMyBatis<Bills> pageMyBatis = billsMapper.selectUserInfoByPage(pagingCriteria);
//        PaginationResult<Bills> paginationResult = PaginationResult.pagination(pageMyBatis,(int)pageMyBatis.getTotal(),queryParams.getPageNo(),queryParams.getPageSize());
//        DataSourceContextHolder.setDbType("dataSource_core");
//        return paginationResult;
//    }
    }


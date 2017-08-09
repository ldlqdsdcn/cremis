package cn.cityre.mis.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.datavip.dao.UserPaymentInfoHistoryMapper;
import cn.cityre.mis.datavip.entity.UserPaymentInfoHistory;
import cn.cityre.mis.datavip.service.UserPaymentInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by cityre on 2017/8/3.
 */
@Service(value = "userPaymentInfoHistoryService")
public class UserPaymentInfoHistoryServiceImpl implements UserPaymentInfoHistoryService{
    @Autowired
    private UserPaymentInfoHistoryMapper userPaymentInfoHistoryMapper;
    @Override
    public UserPaymentInfoHistory getExistPaymentHistoryByBillCode(String billCode) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        UserPaymentInfoHistory userPaymentInfoHistory =  userPaymentInfoHistoryMapper.selectByBillCode(billCode);
        DataSourceContextHolder.setDbType("dataSource_core");
        return userPaymentInfoHistory;
    }

    @Override
    public UserPaymentInfoHistory getExistPaymentHistoryByByPrimaryKey(Integer id) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");

        UserPaymentInfoHistory userPaymentInfoHistory = userPaymentInfoHistoryMapper.selectByPrimaryKey(id);
        DataSourceContextHolder.setDbType("dataSource_core");
        return  userPaymentInfoHistory;

    }

    @Override
    public void createPaymentInfoHistory(UserPaymentInfoHistory userPaymentInfoHistory) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        userPaymentInfoHistoryMapper.insertSelective(userPaymentInfoHistory);
        DataSourceContextHolder.setDbType("dataSource_core");
    }
}

package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.UserPaymentInfoHistoryMapper;
import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfoHistory;
import cn.cityre.mis.cityreaccount.datavip.service.UserPaymentInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cityre on 2017/8/3.
 */
@Service(value = "userPaymentInfoHistoryService")
public class UserPaymentInfoHistoryServiceImpl implements UserPaymentInfoHistoryService {
    @Autowired
    private UserPaymentInfoHistoryMapper userPaymentInfoHistoryMapper;
    @Override
    public UserPaymentInfoHistory getExistPaymentHistoryByBillCode(String billCode) {
        UserPaymentInfoHistory userPaymentInfoHistory =  userPaymentInfoHistoryMapper.selectByBillCode(billCode);
        return userPaymentInfoHistory;
    }

    @Override
    public UserPaymentInfoHistory getExistPaymentHistoryByByPrimaryKey(Integer id) {
        UserPaymentInfoHistory userPaymentInfoHistory = userPaymentInfoHistoryMapper.selectByPrimaryKey(id);
        return  userPaymentInfoHistory;

    }

    @Override
    public void createPaymentInfoHistory(UserPaymentInfoHistory userPaymentInfoHistory) {
        userPaymentInfoHistoryMapper.insertSelective(userPaymentInfoHistory);
    }
}

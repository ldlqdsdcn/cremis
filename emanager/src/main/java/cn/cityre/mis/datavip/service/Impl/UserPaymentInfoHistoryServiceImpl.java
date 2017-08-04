package cn.cityre.mis.datavip.service.Impl;

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
        return userPaymentInfoHistoryMapper.selectByBillCode(billCode);
    }

    @Override
    public void createPaymentInfoHistory(UserPaymentInfoHistory userPaymentInfoHistory) {
        userPaymentInfoHistoryMapper.insertSelective(userPaymentInfoHistory);
    }
}

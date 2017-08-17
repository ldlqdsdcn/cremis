package cn.cityre.mis.cityreaccount.datavip.service;

import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfoHistory;

/**
 * Created by cityre on 2017/8/3.
 */
public interface UserPaymentInfoHistoryService {

    UserPaymentInfoHistory getExistPaymentHistoryByBillCode(String billCode);

    UserPaymentInfoHistory getExistPaymentHistoryByByPrimaryKey(Integer id);

    void createPaymentInfoHistory(UserPaymentInfoHistory userPaymentInfoHistory);
}

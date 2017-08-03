package cn.cityre.mis.datavip.service;

import cn.cityre.mis.datavip.entity.UserPaymentInfo;

/**
 * Created by cityre on 2017/8/3.
 */
public interface UserPaymentInfoService {

    UserPaymentInfo getExistPaymentInfo(String billCode);

    void closeServiceByBillCode(String billCode);
}

package cn.cityre.mis.datavip.service;

import cn.cityre.mis.datavip.entity.UserPaymentInfo;

import java.util.List;

/**
 * Created by cityre on 2017/8/3.
 */
public interface UserPaymentInfoService {

    UserPaymentInfo getExistPaymentInfo(String billCode);

    void closeServiceByBillCode(String billCode);

    List<UserPaymentInfo> getExistPaymentInfoBySuid(String suid);

    void createUserPaymentInfo(UserPaymentInfo userPaymentInfo);


}

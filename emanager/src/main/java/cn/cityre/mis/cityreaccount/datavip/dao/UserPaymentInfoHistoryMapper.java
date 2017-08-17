package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfoHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoHistoryMapper {
    UserPaymentInfoHistory selectByBillCode(String billCode);

    void deleteByPrimaryKey(Integer id);

    void insertSelective(UserPaymentInfoHistory userPaymentInfoHistory);

    UserPaymentInfoHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPaymentInfoHistory record);

}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserPaymentInfoHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoHistoryMapper {
    UserPaymentInfoHistory selectByBillCode(String billCode);

    void deleteByPrimaryKey(Integer id);

    void insertSelective(UserPaymentInfoHistory userPaymentInfoHistory);

    UserPaymentInfoHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPaymentInfoHistory record);

}
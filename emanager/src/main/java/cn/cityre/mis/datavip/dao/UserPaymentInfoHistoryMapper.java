package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserPaymentInfoHistory;

public interface UserPaymentInfoHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPaymentInfoHistory record);

    int insertSelective(UserPaymentInfoHistory record);

    UserPaymentInfoHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPaymentInfoHistory record);

    int updateByPrimaryKey(UserPaymentInfoHistory record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserPaymentInfo;

public interface UserPaymentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPaymentInfo record);

    int insertSelective(UserPaymentInfo record);

    UserPaymentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPaymentInfo record);

    int updateByPrimaryKey(UserPaymentInfo record);
}
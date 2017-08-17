package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfoBackUp;

public interface UserPaymentInfoBak20130128Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPaymentInfoBackUp record);

    int insertSelective(UserPaymentInfoBackUp record);

    UserPaymentInfoBackUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPaymentInfoBackUp record);

    int updateByPrimaryKey(UserPaymentInfoBackUp record);
}
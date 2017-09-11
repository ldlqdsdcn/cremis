package cn.cityre.account.sys.dao;

import cn.cityre.account.sys.model.UserPhone;

public interface UserPhoneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPhone record);

    int insertSelective(UserPhone record);

    UserPhone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPhone record);

    int updateByPrimaryKey(UserPhone record);
}
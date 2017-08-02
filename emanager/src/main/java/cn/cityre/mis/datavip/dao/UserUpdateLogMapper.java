package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserUpdateLog;

public interface UserUpdateLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserUpdateLog record);

    int insertSelective(UserUpdateLog record);

    UserUpdateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserUpdateLog record);

    int updateByPrimaryKey(UserUpdateLog record);
}
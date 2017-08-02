package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserLogin;

public interface UserloginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}
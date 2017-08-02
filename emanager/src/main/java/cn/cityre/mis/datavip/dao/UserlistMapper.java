package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserList;

public interface UserlistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserList record);

    int insertSelective(UserList record);

    UserList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserList record);

    int updateByPrimaryKey(UserList record);
}
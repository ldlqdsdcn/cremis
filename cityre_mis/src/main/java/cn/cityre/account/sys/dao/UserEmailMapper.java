package cn.cityre.account.sys.dao;

import cn.cityre.account.sys.model.UserEmail;

public interface UserEmailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEmail record);

    int insertSelective(UserEmail record);

    UserEmail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEmail record);

    int updateByPrimaryKey(UserEmail record);
}
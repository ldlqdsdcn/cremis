package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserIdentity;

public interface UserIdentityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    UserIdentity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserGroupModule;

public interface UsergroupModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupModule record);

    int insertSelective(UserGroupModule record);

    UserGroupModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroupModule record);

    int updateByPrimaryKey(UserGroupModule record);
}
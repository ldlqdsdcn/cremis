package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UidUserGroup;

public interface UidUsergroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UidUserGroup record);

    int insertSelective(UidUserGroup record);

    UidUserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UidUserGroup record);

    int updateByPrimaryKey(UidUserGroup record);
}
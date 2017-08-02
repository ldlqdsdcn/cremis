package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.PermissionList;

public interface PermissionListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionList record);

    int insertSelective(PermissionList record);

    PermissionList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionList record);

    int updateByPrimaryKey(PermissionList record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserDept;

public interface UserDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDept record);

    int insertSelective(UserDept record);

    UserDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDept record);

    int updateByPrimaryKey(UserDept record);
}
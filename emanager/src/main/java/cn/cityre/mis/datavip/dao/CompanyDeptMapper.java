package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CompanyDept;

public interface CompanyDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyDept record);

    int insertSelective(CompanyDept record);

    CompanyDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyDept record);

    int updateByPrimaryKey(CompanyDept record);
}
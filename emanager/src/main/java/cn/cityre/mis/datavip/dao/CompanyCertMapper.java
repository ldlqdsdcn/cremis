package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CompanyCert;

public interface CompanyCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyCert record);

    int insertSelective(CompanyCert record);

    CompanyCert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyCert record);

    int updateByPrimaryKey(CompanyCert record);
}
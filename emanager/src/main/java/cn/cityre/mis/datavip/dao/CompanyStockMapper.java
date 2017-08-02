package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CompanyStock;

public interface CompanyStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyStock record);

    int insertSelective(CompanyStock record);

    CompanyStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyStock record);

    int updateByPrimaryKey(CompanyStock record);
}
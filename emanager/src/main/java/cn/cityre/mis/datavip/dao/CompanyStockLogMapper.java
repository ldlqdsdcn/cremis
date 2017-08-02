package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CompanyStockLog;

public interface CompanyStockLogMapper {
    int insert(CompanyStockLog record);

    int insertSelective(CompanyStockLog record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CartBills;

public interface CartbillsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CartBills record);

    int insertSelective(CartBills record);

    CartBills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartBills record);

    int updateByPrimaryKey(CartBills record);
}
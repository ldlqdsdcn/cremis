package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CartBills;
import org.springframework.stereotype.Repository;

@Repository
public interface CartbillsMapper {

    void updateByBigBillCode(CartBills cartBills);

    int deleteByPrimaryKey(Integer id);

    int insert(CartBills record);

    int insertSelective(CartBills record);

    CartBills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartBills record);

    int updateByPrimaryKey(CartBills record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.CartBills;
import org.springframework.stereotype.Repository;

@Repository
public interface CartbillsMapper {

    void updateByBigBillCode(CartBills cartBills);

    int deleteByPrimaryKey(Integer id);

    int insert(CartBills cartBills);

    CartBills selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(CartBills cartBills);

}
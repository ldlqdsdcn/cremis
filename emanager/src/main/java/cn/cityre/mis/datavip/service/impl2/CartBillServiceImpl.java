package cn.cityre.mis.datavip.service.impl2;

import cn.cityre.mis.datavip.dao.CartbillsMapper;
import cn.cityre.mis.datavip.entity.CartBills;
import cn.cityre.mis.datavip.service.CartBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cityre on 2017/8/3.
 */
@Service(value = "cartBillService")
public class CartBillServiceImpl implements CartBillService{
    @Autowired
    private CartbillsMapper cartbillsMapper;
    @Override
    public void updateExistCartBillByBillCode(CartBills cartBills) {
        cartbillsMapper.updateByBigBillCode(cartBills);
    }
}

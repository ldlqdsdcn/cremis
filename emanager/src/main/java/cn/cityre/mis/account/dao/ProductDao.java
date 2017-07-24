package cn.cityre.mis.account.dao;

import cn.cityre.mis.account.entity.po.MisProductPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface ProductDao {
    MisProductPo selectProductByCode(String productCode);
    void updateProduct(MisProductPo misProductPo);
    void deleteByProductCode(String productCode);
    void createProduct(MisProductPo misProductPo);
    List<MisProductPo> selectAllProducts();
}

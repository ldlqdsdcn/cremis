package cn.cityre.edi.mis.base.dao;

import cn.cityre.edi.mis.base.entity.po.MisProductPo;
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

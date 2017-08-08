package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisProductPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisProductMapper {
    MisProductPo selectProductByCode(String productCode);

    void updateProduct(MisProductPo misProductPo);

    void deleteByProductCode(String productCode);

    void createProduct(MisProductPo misProductPo);

    List<MisProductPo> selectAllProducts();
}

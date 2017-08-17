package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisProductPo;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisProductMapper {
    PageMyBatis<MisProductPo> selectByPage(PagingCriteria pagingCriteria);
    MisProductPo selectProductByCode(String productCode);

    void updateProduct(MisProductPo misProductPo);

    void deleteByProductCode(String productCode);

    void createProduct(MisProductPo misProductPo);

    List<MisProductPo> selectAllProducts();
}

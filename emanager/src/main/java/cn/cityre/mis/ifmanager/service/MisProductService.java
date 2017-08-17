package cn.cityre.mis.ifmanager.service;

import cn.cityre.mis.ifmanager.entity.MisProductPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
public interface MisProductService {
    PaginationResult<MisProductPo> getProductListByMybatis(List<SearchField> search, QueryParams queryParams);

    PaginationResult<MisProductPo> getProductList(Search search, QueryParams queryParams);

    void updateProduct(MisProductPo misProductPo);

    void createProduct(MisProductPo misProductPo);

    void deleteProducts(String[] productCode);

    MisProductPo getExistProduct(String productCode);

    boolean findExistProductByProductCode(String productCode);
}

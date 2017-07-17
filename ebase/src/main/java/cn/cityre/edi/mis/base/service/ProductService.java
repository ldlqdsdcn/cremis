package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisProductPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/11.
 */
public interface ProductService {
    PaginationResult<MisProductPo> getProductList(Search search, QueryParams queryParams);

    void saveProduct(MisProductPo misProductPo);

    void deleteProducts(String[] productCode);

    MisProductPo getExistProduct(String productCode);

    boolean findExistProductByProductCode(String productCode);

}

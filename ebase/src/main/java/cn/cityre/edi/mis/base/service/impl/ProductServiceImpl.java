package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.ProductPo;
import cn.cityre.edi.mis.base.service.ProductService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
    @DataAccess(entity = ProductPo.class)
    private CommonDao<ProductPo, String> productDao;

    @Override
    public PaginationResult<ProductPo> getProductList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getTotalRecords());
        PaginationResult<ProductPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<ProductPo> searchResult = productDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<ProductPo> productPoList = productDao.search(search);
            paginationResult = PaginationResult.pagination(productPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public void saveProduct(ProductPo productPo) {
        productDao.save(productPo);
    }

    @Override
    public void deleteProducts(String[] productCode) {
        productDao.removeByIds(productCode);
    }

    @Override
    public ProductPo getExistProduct(String productCode) {
        ProductPo productPo = productDao.find(productCode);
        return productPo;
    }

    @Override
    public boolean findExistProductByProductCode(String productCode) {
        ProductPo productPo = productDao.find(productCode);
        if (productPo == null) {
            return false;
        } else {
            return true;
        }
    }


}

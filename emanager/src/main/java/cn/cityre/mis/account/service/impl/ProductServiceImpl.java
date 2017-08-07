package cn.cityre.mis.account.service.impl;


import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.account.dao.ProductDao;
import cn.cityre.mis.account.entity.po.MisProductPo;
import cn.cityre.mis.account.service.ProductService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
    @DataAccess(entity = MisProductPo.class)
    private CommonDao<MisProductPo, String> productDao;
    @Autowired
    private ProductDao productMapper;

    @Override
    public PaginationResult<MisProductPo> getProductList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisProductPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<MisProductPo> searchResult = productDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<MisProductPo> misProductPoList = productDao.search(search);
            paginationResult = PaginationResult.pagination(misProductPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public void updateProduct(MisProductPo misProductPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        productMapper.updateProduct(misProductPo);
    }
    @Override
    public void createProduct(MisProductPo misProductPo){
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        productMapper.createProduct(misProductPo);
    }

    @Override
    public void deleteProducts(String[] productCode) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        productDao.removeByIds(productCode);
    }

    @Override
    public MisProductPo getExistProduct(String productCode) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisProductPo misProductPo = productMapper.selectProductByCode(productCode);
        return misProductPo;
    }

    @Override
    public boolean findExistProductByProductCode(String productCode) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisProductPo misProductPo = productMapper.selectProductByCode(productCode);
        if (misProductPo == null) {
            return false;
        } else {
            return true;
        }
    }



}

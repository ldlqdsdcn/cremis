package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.ApiKeyPo;
import cn.cityre.edi.mis.base.service.ApiKeyService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/10.
 */
@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    @DataAccess(entity = ApiKeyPo.class)
    private CommonDao<ApiKeyPo,Integer> apiKeyDao;
    @Override
    public PaginationResult<ApiKeyPo> getApiKeyList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        search.addFilterEqual("isValid","1");
        PaginationResult<ApiKeyPo> paginationResult = null;
        if (queryParams.isInit()){
            SearchResult<ApiKeyPo> searchResult = apiKeyDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else {
            List<ApiKeyPo> apiKeyPoList = apiKeyDao.search(search);
            paginationResult = PaginationResult.pagination(apiKeyPoList,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public void saveApiKey(ApiKeyPo apiKeyPo) {
        apiKeyDao.save(apiKeyPo);
    }

    @Override
    public void deleteApiKey(Integer[] ids) {
        Search search = new Search();
        search.addFilterIn("id",ids);
        List<ApiKeyPo> apiKeyPoList = apiKeyDao.search(search);
        apiKeyPoList.forEach(e->{
            e.setIsValid((byte) 0);
        });
    }

    @Override
    public ApiKeyPo getApiKey(Integer id) {
        ApiKeyPo apiKeyPo = apiKeyDao.find(id);
        return apiKeyPo;
    }
}

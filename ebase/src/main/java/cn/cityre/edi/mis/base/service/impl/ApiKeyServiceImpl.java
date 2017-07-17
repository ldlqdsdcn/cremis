package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.MisApiKeyPo;
import cn.cityre.edi.mis.base.service.ApiKeyService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/10.
 */
@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    @DataAccess(entity = MisApiKeyPo.class)
    private CommonDao<MisApiKeyPo,Integer> apiKeyDao;
    @Override
    public PaginationResult<MisApiKeyPo> getApiKeyList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        search.addFilterEqual("isValid","1");
        PaginationResult<MisApiKeyPo> paginationResult = null;
        if (queryParams.isInit()){
            SearchResult<MisApiKeyPo> searchResult = apiKeyDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else {
            List<MisApiKeyPo> misApiKeyPoList = apiKeyDao.search(search);
            paginationResult = PaginationResult.pagination(misApiKeyPoList,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public void saveApiKey(MisApiKeyPo misApiKeyPo) {
        apiKeyDao.save(misApiKeyPo);
    }

    @Override
    public void deleteApiKey(Integer[] ids) {
        Search search = new Search();
        search.addFilterIn("id",ids);
        List<MisApiKeyPo> misApiKeyPoList = apiKeyDao.search(search);
        misApiKeyPoList.forEach(e->{
            e.setIsValid((byte) 0);
        });
    }

    @Override
    public MisApiKeyPo getApiKey(Integer id) {
        MisApiKeyPo misApiKeyPo = apiKeyDao.find(id);
        return misApiKeyPo;
    }
}

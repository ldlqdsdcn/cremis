package cn.cityre.mis.account.service.Impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.RandomUtil;
import cn.cityre.mis.account.dao.ApiKeyDao;
import cn.cityre.mis.account.entity.po.MisApiKeyPo;
import cn.cityre.mis.account.service.ApiKeyService;
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
 * Created by cityre on 2017/7/10.
 */
@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    //HibernateDao
    @DataAccess(entity = MisApiKeyPo.class)
    private CommonDao<MisApiKeyPo,Integer> apiKeyDao;
    //MybatisDao
    @Autowired
    private ApiKeyDao apiKeyMapper;
    @Override
    public PaginationResult<MisApiKeyPo> getApiKeyList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSourceAccount");
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
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
    public void createApiKey(MisApiKeyPo misApiKeyPo) {
        misApiKeyPo.setApiKey(RandomUtil.base62UUID());
        apiKeyMapper.createApiKey(misApiKeyPo);
    }

    @Override
    public void updateApikey(MisApiKeyPo misApiKeyPo) {
        apiKeyMapper.updateByKey(misApiKeyPo);
    }

    @Override
    public void logicDeleteApiKey(Integer[] ids) {
       for (int i=0;i<ids.length;i++){
           apiKeyMapper.logicDeleteById(ids[i]);
       }
    }

    @Override
    public MisApiKeyPo getApiKey(String apiKey) {
        MisApiKeyPo misApiKeyPo = apiKeyMapper.selectByKey(apiKey);
        return misApiKeyPo;
    }

    @Override
    public void deleteById(Integer[] ids) {
        apiKeyDao.removeByIds(ids);
    }

}

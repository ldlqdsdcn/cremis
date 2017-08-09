package cn.cityre.mis.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.edi.mis.base.util.RandomUtil;
import cn.cityre.mis.ifmanager.dao.MisApiKeyMapper;
import cn.cityre.mis.ifmanager.entity.MisApiKeyPo;
import cn.cityre.mis.ifmanager.service.MisApiKeyService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import javafx.scene.control.Pagination;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Service(value = "misApiKeyService")
public class MisApiKeyServiceImpl implements MisApiKeyService {
    //HibernateDao
    @DataAccess(entity = MisApiKeyPo.class)
    private CommonDao<MisApiKeyPo,Integer> misApiKeyDao;
    //MybatisDao
    @Autowired
    private MisApiKeyMapper misApiKeyMapper;
    @Override
    public PaginationResult<MisApiKeyPo> getApiKeyList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisApiKeyPo> paginationResult = null;
        if (queryParams.isInit()){
            SearchResult<MisApiKeyPo> searchResult = misApiKeyDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else {
            List<MisApiKeyPo> misApiKeyPoList = misApiKeyDao.search(search);
            paginationResult = PaginationResult.pagination(misApiKeyPoList,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public PaginationResult<MisApiKeyPo> getApiKeyListByMybatis(List<SearchField> searchFields, QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSource_account");
        PagingCriteria pagingCriteria = PagingCriteria.createCriteriaWithSearch(queryParams.getFirstResult(),queryParams.getPageSize(),queryParams.getPageNo(),searchFields);
        PageMyBatis<MisApiKeyPo> pageMyBatis = misApiKeyMapper.selectByPage(pagingCriteria);
        PaginationResult<MisApiKeyPo> poPaginationResult = PaginationResult.pagination(pageMyBatis,(int)pageMyBatis.getTotal(),queryParams.getPageNo(),queryParams.getPageSize());
        DataSourceContextHolder.setDbType("dataSource_core");
        return poPaginationResult;
    }

    @Override
    public void createApiKey(MisApiKeyPo misApiKeyPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misApiKeyPo.setApiKey(RandomUtil.base62UUID());
        DataSourceContextHolder.setDbType("dataSource_core");
        misApiKeyMapper.createApiKey(misApiKeyPo);
    }

    @Override
    public void updateApikey(MisApiKeyPo misApiKeyPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misApiKeyMapper.updateByKey(misApiKeyPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void logicDeleteApiKey(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        for (int i=0;i<ids.length;i++){
            misApiKeyMapper.logicDeleteById(ids[i]);
        }
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public MisApiKeyPo getApiKey(String apiKey) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisApiKeyPo misApiKeyPo = misApiKeyMapper.selectByKey(apiKey);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misApiKeyPo;

    }

    @Override
    public void deleteById(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misApiKeyDao.removeByIds(ids);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

}

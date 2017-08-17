package cn.cityre.mis.account.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.account.ifmanager.dao.MisEmailMapper;
import cn.cityre.mis.account.ifmanager.entity.MisUserEmailPo;
import cn.cityre.mis.account.ifmanager.service.MisEmailService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/19.
 */
@Service
public class MisEmailServiceImpl implements MisEmailService {
    //MybatisDao
    @Autowired
    private MisEmailMapper misEmailMapper;
    //hibernateDao
    @DataAccess(entity = MisUserEmailPo.class)
    private CommonDao<MisUserEmailPo, Integer> misUserEmailDao;

    @Override
    public PaginationResult<MisUserEmailPo> getUserEmailListByMybatis(List<SearchField> searchFields, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(),queryParams.getFirstResult(),queryParams.getPageNo());
        Map<String,Object>map = new HashMap<>();
        map.put("pagingCriteria",pagingCriteria);
        if (searchFields==null){
            map.put("unionUid",null);
        }else{
            for (SearchField searchField:searchFields)
                if (searchField.getField().equals("unionUid")){
                    map.put("unionUid",searchField.getValue());
                }
        }
        PageMyBatis<MisUserEmailPo> pageMyBatis = misEmailMapper.selectByPage(map);
        PaginationResult<MisUserEmailPo> paginationResult = PaginationResult.pagination(pageMyBatis,(int)pageMyBatis.getTotal(),queryParams.getPageNo(),queryParams.getPageSize());
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public PaginationResult<MisUserEmailPo> getUserEmailList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisUserEmailPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<MisUserEmailPo> searchResult = misUserEmailDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<MisUserEmailPo> list = misUserEmailDao.search(search);
            paginationResult = PaginationResult.pagination(list, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }


    @Override
    public void updateEmailByUid(MisUserEmailPo misUserEmailPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misEmailMapper.updateByUid(misUserEmailPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public List<MisUserEmailPo> getEmaliList() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserEmailPo> userEmailPos = misEmailMapper.selectEmailList();
        return userEmailPos;
    }

    @Override
    public void createEmail(MisUserEmailPo misUserEmailPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misEmailMapper.createEmail(misUserEmailPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void updateEmailById(MisUserEmailPo misUserEmailPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misEmailMapper.updateById(misUserEmailPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void deleteEmailById(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        for (int i = 0; i < ids.length; i++) {
            misEmailMapper.deleteById(ids[i]);
        }
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public MisUserEmailPo getExistUserEmailById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisUserEmailPo misUserEmailPo =  misEmailMapper.selectById(id);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserEmailPo;

    }
}

package cn.cityre.mis.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.ifmanager.dao.MisPhonePinMapper;
import cn.cityre.mis.ifmanager.entity.MisPhonePinPo;
import cn.cityre.mis.ifmanager.entity.MisUserPhonePo;
import cn.cityre.mis.ifmanager.service.MisPhonePinService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.mybatis.pagination.PagingParametersFinder;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/8.
 */
@Service(value = "misPhonePinService")
public class MisPhonePinServiceImpl implements MisPhonePinService {
    @Autowired
    private MisPhonePinMapper misPhonePinMapper;
    @DataAccess(entity = MisPhonePinPo.class)
    private CommonDao<MisPhonePinPo, Integer> misPhonePinDao;

    @Override
    public PaginationResult<MisPhonePinPo> getMisPhonePinListMybatis(List<SearchField> search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(),queryParams.getFirstResult(),queryParams.getPageNo());
        Map<String,Object> map = new HashMap<>();
        map.put("pagingCriteria",pagingCriteria);
        if (search == null) {
            map.put("phone", null);
            map.put("action",null);
        } else {
            for (SearchField searchField : search) {
                if (searchField.getField().equals("phone")) {
                    map.put("phone", searchField.getValue());
                    continue;
                }else if (searchField.getField().equals("action")){
                    map.put("action", searchField.getValue());
                    continue;
                }else {
                    map.put("phone", null);
                    map.put("action",null);
                }
            }
        }
        PageMyBatis<MisPhonePinPo> pageMyBatis =misPhonePinMapper.selectByPage(map);
        Integer total = misPhonePinMapper.selectCount(map);
        PaginationResult<MisPhonePinPo>  paginationResult = PaginationResult.pagination(pageMyBatis,(int)pageMyBatis.getTotal(),queryParams.getPageNo(),queryParams.getPageSize());
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public PaginationResult<MisPhonePinPo> getMisPhonePinList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisPhonePinPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<MisPhonePinPo> searchResult = misPhonePinDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<MisPhonePinPo> list = misPhonePinDao.search(search);
            paginationResult = PaginationResult.pagination(list, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinByPhone(String phone) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisPhonePinPo> misPhonePinPos =  misPhonePinMapper.selectByPhone(phone);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misPhonePinPos;
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinList() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisPhonePinPo> misPhonePinPos =  misPhonePinMapper.selectAllList();
        DataSourceContextHolder.setDbType("dataSource_core");
        return misPhonePinPos;
    }

    @Override
    public MisPhonePinPo getPhonePinById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisPhonePinPo misPhonePinPo= misPhonePinMapper.selectById(id);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misPhonePinPo;
    }

    @Override
    public void createPhonePin(MisPhonePinPo misPhonePinPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhonePinMapper.createPhonePin(misPhonePinPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void updatePhonePin(MisPhonePinPo misPhonePinPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhonePinMapper.updateById(misPhonePinPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public void deleteById(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        for (int i = 0; i < ids.length; i++) {
            misPhonePinMapper.deleteById(ids[i]);
        }
        DataSourceContextHolder.setDbType("dataSource_core");
    }
}

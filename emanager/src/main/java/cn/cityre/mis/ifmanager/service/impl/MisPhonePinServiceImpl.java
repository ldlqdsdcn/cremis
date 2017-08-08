package cn.cityre.mis.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.ifmanager.dao.MisPhonePinMapper;
import cn.cityre.mis.ifmanager.entity.MisPhonePinPo;
import cn.cityre.mis.ifmanager.service.MisPhonePinService;
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
 * Created by cityre on 2017/8/8.
 */
@Service(value = "misPhonePinService")
public class MisPhonePinServiceImpl implements MisPhonePinService {
    @Autowired
    private MisPhonePinMapper misPhonePinMapper;
    @DataAccess(entity = MisPhonePinPo.class)
    private CommonDao<MisPhonePinPo, Integer> misPhonePinDao;

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
        return paginationResult;
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinByPhone(String phone) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misPhonePinMapper.selectByPhone(phone);
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinList() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misPhonePinMapper.selectAllList();
    }

    @Override
    public MisPhonePinPo getPhonePinById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misPhonePinMapper.selectById(id);
    }

    @Override
    public void createPhonePin(MisPhonePinPo misPhonePinPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhonePinMapper.createPhonePin(misPhonePinPo);
    }

    @Override
    public void updatePhonePin(MisPhonePinPo misPhonePinPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misPhonePinMapper.updateById(misPhonePinPo);
    }

    @Override
    public void deleteById(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        for (int i = 0; i < ids.length; i++) {
            misPhonePinMapper.deleteById(ids[i]);
        }
    }
}

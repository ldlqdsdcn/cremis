package cn.cityre.mis.account.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.account.dao.MisPhonePinDao;
import cn.cityre.mis.account.entity.po.MisPhonePinPo;
import cn.cityre.mis.account.service.PhonePinService;
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
 * Created by cityre on 2017/7/19.
 */
@Service
public class PhonePinServiceImpl implements PhonePinService {
    @Autowired
    private MisPhonePinDao misPhonePinMapper;
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
        DataSourceContextHolder.setDbType("dataSource_core");

        return paginationResult;
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinByPhone(String phone) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisPhonePinPo> list = misPhonePinMapper.selectByPhone(phone);
        DataSourceContextHolder.setDbType("dataSource_core");

        return list;
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinList() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisPhonePinPo> list = misPhonePinMapper.selectAllList();
        DataSourceContextHolder.setDbType("dataSource_core");
        return list;
    }

    @Override
    public MisPhonePinPo getPhonePinById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisPhonePinPo misPhonePinPo = misPhonePinMapper.selectById(id);
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

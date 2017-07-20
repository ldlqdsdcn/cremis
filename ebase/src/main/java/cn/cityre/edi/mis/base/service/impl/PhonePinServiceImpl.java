package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.dao.MisPhonePinDao;
import cn.cityre.edi.mis.base.entity.po.MisPhonePinPo;
import cn.cityre.edi.mis.base.service.PhonePinService;
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
        return misPhonePinMapper.selectByPhone(phone);
    }

    @Override
    public List<MisPhonePinPo> getExistPhonePinList() {
        return misPhonePinMapper.selectAllList();
    }

    @Override
    public MisPhonePinPo getPhonePinById(Integer id) {
        return misPhonePinMapper.selectById(id);
    }

    @Override
    public void createPhonePin(MisPhonePinPo misPhonePinPo) {
        misPhonePinMapper.createPhonePin(misPhonePinPo);
    }

    @Override
    public void updatePhonePin(MisPhonePinPo misPhonePinPo) {
        misPhonePinMapper.updateById(misPhonePinPo);
    }

    @Override
    public void deleteById(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            misPhonePinMapper.deleteById(ids[i]);
        }
    }
}

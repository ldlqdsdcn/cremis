package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.MisPhonePinPo;
import cn.cityre.edi.mis.base.service.MisPhonePinService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/12.
 */
@Service(value = "misPhonePinService")
public class MisPhonePinServiceImpl implements MisPhonePinService {
    @DataAccess(entity = MisPhonePinPo.class)
    private CommonDao<MisPhonePinPo,Integer> misPhonePinDao;
    @Override
    public PaginationResult<MisPhonePinPo> getMisPhonePinList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisPhonePinPo> paginationResult = null;
        if (queryParams.isInit()){
            SearchResult<MisPhonePinPo> searchResult = misPhonePinDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else{
            List<MisPhonePinPo> list = misPhonePinDao.search(search);
            paginationResult = PaginationResult.pagination(list,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public MisPhonePinPo getPhonePin(Integer id) {
        MisPhonePinPo misPhonePinPo = misPhonePinDao.find(id);
        return misPhonePinPo;
    }

    @Override
    public void savePhonePin(MisPhonePinPo misPhonePinPo) {
        misPhonePinDao.save(misPhonePinPo);
    }

    @Override
    public void deletePhonePin(Integer[] ids) {
        misPhonePinDao.removeByIds(ids);
    }
}

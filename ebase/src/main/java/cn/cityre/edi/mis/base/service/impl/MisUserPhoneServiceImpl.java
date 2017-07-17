package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.MisUserPhonePo;
import cn.cityre.edi.mis.base.service.MisUserPhoneService;
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
@Service(value = "misUserPhoneService")
public class MisUserPhoneServiceImpl implements MisUserPhoneService {
    @DataAccess(entity = MisUserPhonePo.class)
    private CommonDao<MisUserPhonePo, Integer> misUserPhoneDao;

    @Override
    public PaginationResult<MisUserPhonePo> getUserPhoneList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisUserPhonePo> paginationResult = null;
        if (queryParams.isInit()){
            SearchResult<MisUserPhonePo> searchResult = misUserPhoneDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else{
            List<MisUserPhonePo> list = misUserPhoneDao.search(search);
            paginationResult = PaginationResult.pagination(list,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public MisUserPhonePo getUserPhone(Integer id) {
        MisUserPhonePo misUserPhonePo = misUserPhoneDao.find(id);
        return misUserPhonePo;
    }

    @Override
    public void saveUserPhone(MisUserPhonePo misUserPhonePo) {
        misUserPhoneDao.save(misUserPhonePo);
    }

    @Override
    public void deleteUserPhone(Integer[] ids) {
        misUserPhoneDao.removeByIds(ids);
    }
}

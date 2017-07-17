package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.MisUserEmailPo;
import cn.cityre.edi.mis.base.service.MisUserEmailService;
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
@Service(value = "misUserEmailService")
public class MisUserEmailServiceImpl implements MisUserEmailService {
    @DataAccess(entity = MisUserEmailPo.class)
    private CommonDao<MisUserEmailPo,Integer> misUserEmailDao;
    @Override
    public PaginationResult<MisUserEmailPo> getUserEmailList(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<MisUserEmailPo> paginationResult = null;
        if (queryParams.isInit()){
            SearchResult<MisUserEmailPo> searchResult = misUserEmailDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else{
            List<MisUserEmailPo> list = misUserEmailDao.search(search);
            paginationResult = PaginationResult.pagination(list,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public MisUserEmailPo getUserEmail(Integer id) {
        MisUserEmailPo misUserEmailPo = misUserEmailDao.find(id);
        return misUserEmailPo;
    }

    @Override
    public void saveUserEmail(MisUserEmailPo misUserEmailPo) {
        misUserEmailDao.save(misUserEmailPo);
    }

    @Override
    public void deleteUserEmail(Integer[] ids) {
        misUserEmailDao.removeByIds(ids);
    }
}

package cn.cityre.mis.account.service.Impl;

import cn.cityre.mis.account.dao.MisEmailDao;
import cn.cityre.mis.account.entity.po.MisUserEmailPo;
import cn.cityre.mis.account.service.MisEmailService;
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
public class MisEmailServiceImpl implements MisEmailService {
    //MybatisDao
    @Autowired
    private MisEmailDao misEmailMapper;
    //hibernateDao
    @DataAccess(entity = MisUserEmailPo.class)
    private CommonDao<MisUserEmailPo, Integer> misUserEmailDao;

    @Override
    public PaginationResult<MisUserEmailPo> getUserEmailList(Search search, QueryParams queryParams) {
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
        return paginationResult;
    }

    @Override
    public MisUserEmailPo getExistEmailByUid(String unionUid, Byte isVerified, Byte isPrimary) {
        return misEmailMapper.selectByUid(unionUid, isVerified, isPrimary);
    }

    @Override
    public void updateEmailByUid(MisUserEmailPo misUserEmailPo) {
        misEmailMapper.updateByUid(misUserEmailPo);
    }

    @Override
    public List<MisUserEmailPo> getEmaliList() {
        return misEmailMapper.selectEmailList();
    }

    @Override
    public void createEmail(MisUserEmailPo misUserEmailPo) {
        misEmailMapper.createEmail(misUserEmailPo);
    }

    @Override
    public void updateEmailById(MisUserEmailPo misUserEmailPo) {
        misEmailMapper.updateById(misUserEmailPo);
    }

    @Override
    public void deleteEmailById(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            misEmailMapper.deleteById(ids[i]);
        }
    }

    @Override
    public MisUserEmailPo getExistUserEmailById(Integer id) {
        return misEmailMapper.selectById(id);
    }
}

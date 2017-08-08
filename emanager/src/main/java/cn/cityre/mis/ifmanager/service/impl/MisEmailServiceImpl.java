package cn.cityre.mis.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.ifmanager.dao.MisEmailMapper;
import cn.cityre.mis.ifmanager.entity.MisUserEmailPo;
import cn.cityre.mis.ifmanager.service.MisEmailService;
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
    private MisEmailMapper misEmailMapper;
    //hibernateDao
    @DataAccess(entity = MisUserEmailPo.class)
    private CommonDao<MisUserEmailPo, Integer> misUserEmailDao;

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
        return paginationResult;
    }

//    @Override
//    public MisUserEmailPo getExistEmailByUid(String unionUid, Byte isVerified, Byte isPrimary) {
//        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
//        return misEmailMapper.selectById(unionUid, isVerified, isPrimary);
//    }

    @Override
    public void updateEmailByUid(MisUserEmailPo misUserEmailPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misEmailMapper.updateByUid(misUserEmailPo);
    }

    @Override
    public List<MisUserEmailPo> getEmaliList() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misEmailMapper.selectEmailList();
    }

    @Override
    public void createEmail(MisUserEmailPo misUserEmailPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misEmailMapper.createEmail(misUserEmailPo);
    }

    @Override
    public void updateEmailById(MisUserEmailPo misUserEmailPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misEmailMapper.updateById(misUserEmailPo);
    }

    @Override
    public void deleteEmailById(Integer[] ids) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        for (int i = 0; i < ids.length; i++) {
            misEmailMapper.deleteById(ids[i]);
        }
    }

    @Override
    public MisUserEmailPo getExistUserEmailById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misEmailMapper.selectById(id);
    }
}

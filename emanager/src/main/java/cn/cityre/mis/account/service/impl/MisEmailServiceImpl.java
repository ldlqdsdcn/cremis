package cn.cityre.mis.account.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
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
    public MisUserEmailPo getExistEmailByUid(String unionUid, Byte isVerified, Byte isPrimary) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisUserEmailPo misUserEmailPo = misEmailMapper.selectByUid(unionUid, isVerified, isPrimary);
        DataSourceContextHolder.setDbType("dataSource_core");

        return misUserEmailPo;
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
        List<MisUserEmailPo> list= misEmailMapper.selectEmailList();
        DataSourceContextHolder.setDbType("dataSource_core");
        return list;
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
        MisUserEmailPo misUserEmailPo= misEmailMapper.selectById(id);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserEmailPo;
    }
}

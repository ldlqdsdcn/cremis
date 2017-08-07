package cn.cityre.mis.account.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.account.dao.MisUserDao;
import cn.cityre.mis.account.entity.po.MisUserPo;
import cn.cityre.mis.account.service.MisUserService;
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
public class MisUserServiceImpl implements MisUserService {
    @Autowired
    private MisUserDao misUserDao;
    @DataAccess(entity = MisUserPo.class)
    private CommonDao<MisUserPo,Integer> userDao;
    @Override
    public PaginationResult<MisUserPo> getExistUserList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        PaginationResult<MisUserPo> poPaginationResult = null;
        search.setMaxResults(queryParams.getPageSize());
        search.setFirstResult(queryParams.getFirstResult());

        if (queryParams.isInit()){
            SearchResult searchResult = userDao.searchAndCount(search);
            poPaginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else{
            List<MisUserPo> misUserPoList = userDao.search(search);
            poPaginationResult = PaginationResult.pagination(misUserPoList,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return poPaginationResult;
    }

    @Override
    public MisUserPo selectByUserId(String userId) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misUserDao.selectByUserId(userId);
    }

    @Override
    public MisUserPo selectByUid(String uid) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misUserDao.selectByUid(uid);
    }

    @Override
    public List<MisUserPo> selectByRealName(String RealName) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misUserDao.selectByRealName(RealName);
    }

    @Override
    public List<MisUserPo> selectByTime(String creatStartTime, String createEndTime) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misUserDao.selectByCreateTime(creatStartTime, createEndTime);
    }

    @Override
    public void updateById(MisUserPo misUserPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misUserDao.updateById(misUserPo);
    }

    @Override
    public MisUserPo getExistUserById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        return misUserDao.selectById(id);
    }
}

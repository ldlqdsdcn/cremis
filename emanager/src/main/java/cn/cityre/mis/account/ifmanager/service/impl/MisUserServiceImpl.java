package cn.cityre.mis.account.ifmanager.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.account.ifmanager.dao.MisUserMapper;
import cn.cityre.mis.account.ifmanager.entity.MisUserPo;
import cn.cityre.mis.account.ifmanager.service.MisUserService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/19.
 */
@Service
public class MisUserServiceImpl implements MisUserService {
    @Autowired
    private MisUserMapper misUserMapper;
    @DataAccess(entity = MisUserPo.class)
    private CommonDao<MisUserPo, Integer> userDao;

    @Override
    public PaginationResult<MisUserPo> getExistUserList(Search search, QueryParams queryParams) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        PaginationResult<MisUserPo> poPaginationResult = null;
        search.setMaxResults(queryParams.getPageSize());
        search.setFirstResult(queryParams.getFirstResult());
        if (queryParams.isInit()) {
            SearchResult searchResult = userDao.searchAndCount(search);
            poPaginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<MisUserPo> misUserPoList = userDao.search(search);
            poPaginationResult = PaginationResult.pagination(misUserPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return poPaginationResult;
    }

    @Override
    public List<MisUserPo> getNewUser() {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserPo> list = misUserMapper.selectUserOrderByCreateTime();
        DataSourceContextHolder.setDbType("dataSource_core");
        return list;
    }

    @Override
    public PaginationResult<MisUserPo> getExistUserListByMybatis(List<SearchField> searchFields, QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSource_account");
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(), queryParams.getFirstResult(), queryParams.getPageNo());
        Map<String, Object> map = new HashMap<>();
        map.put("pagingCriteria", pagingCriteria);
        if (searchFields == null) {
            map.put("realName", null);
            map.put("isVerified", null);
            map.put("createStartTime", null);
            map.put("createEndTime", null);
        } else {
            for (SearchField searchField : searchFields) {
                if (searchField.getField().equals("realName")) {
                    map.put("realName", searchField.getValue());
                    continue;
                } else if (searchField.getField().equals("isVerified")) {
                    map.put("isVerified", searchField.getValue());
                    continue;
                } else if (searchField.getField().equals("createStartTime")) {
                    map.put("createStartTime", searchField.getValue());
                    continue;
                } else if (searchField.getField().equals("createEndTime")) {
                    map.put("createEndTime", searchField.getValue());
                } else {
                    map.put("realName", null);
                    map.put("isVerified", null);
                    map.put("createStartTime", null);
                    map.put("createEndTime", null);
                }
            }
        }
        PageMyBatis<MisUserPo> pageMyBatis = misUserMapper.selectByPage(map);
        PaginationResult<MisUserPo> poPaginationResult = PaginationResult.pagination(pageMyBatis, (int) pageMyBatis.getTotal(), queryParams.getPageNo(), queryParams.getPageSize());
        DataSourceContextHolder.setDbType("dataSource_core");
        return poPaginationResult;
    }

    @Override
    public MisUserPo selectByUserId(String userId) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisUserPo misUserPo = misUserMapper.selectByUserId(userId);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPo;
    }

    @Override
    public MisUserPo selectByUid(String uid) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisUserPo misUserPo = misUserMapper.selectByUid(uid);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPo;
    }

    @Override
    public List<MisUserPo> selectByRealName(String RealName) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserPo> misUserPos = misUserMapper.selectByRealName(RealName);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPos;
    }

    @Override
    public List<MisUserPo> selectByTime(String creatStartTime, String createEndTime) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        List<MisUserPo> misUserPos = misUserMapper.selectByCreateTime(creatStartTime, createEndTime);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPos;
    }

    @Override
    public void updateById(MisUserPo misUserPo) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        misUserMapper.updateById(misUserPo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }

    @Override
    public MisUserPo getExistUserById(Integer id) {
        DataSourceContextHolder.setDbType(DataSourceEnum.account.value());
        MisUserPo misUserPo = misUserMapper.selectById(id);
        DataSourceContextHolder.setDbType("dataSource_core");
        return misUserPo;
    }
}

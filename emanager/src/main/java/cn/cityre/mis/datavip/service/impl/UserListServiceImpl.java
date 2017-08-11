package cn.cityre.mis.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.datavip.dao.UserListMapper;
import cn.cityre.mis.datavip.entity.UserList;
import cn.cityre.mis.datavip.service.UserListService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/9.
 */
@Service(value = "userListService")
public class UserListServiceImpl implements UserListService {
    @Autowired
    private UserListMapper userListMapper;
    @Override
    public PaginationResult<UserList> getExistUserInfoList(Map<String,Object> map, QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        PagingCriteria pagingCriteria =PagingCriteria.createCriteria(queryParams.getFirstResult(),queryParams.getPageSize(),queryParams.getPageNo());
        List<UserList> pageMyBatis = userListMapper.selectUserInfoByPage(map);
        PaginationResult<UserList> paginationResult = PaginationResult.pagination(pageMyBatis,pageMyBatis.size(),queryParams.getPageNo(),queryParams.getPageSize());
        DataSourceContextHolder.setDbType("dataSource_core");
        return paginationResult;
    }

    @Override
    public UserList getExistUserListBySuid(String suid) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        UserList userList = userListMapper.selectBySuid(suid);
        DataSourceContextHolder.setDbType("dataSource_core");
        return userList;
    }
}

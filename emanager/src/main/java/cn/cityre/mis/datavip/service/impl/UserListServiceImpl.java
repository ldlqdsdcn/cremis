package cn.cityre.mis.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.datavip.dao.UserListMapper;
import cn.cityre.mis.datavip.dto.SearchParams;
import cn.cityre.mis.datavip.entity.UserList;
import cn.cityre.mis.datavip.service.UserListService;
import cn.cityre.mis.ifmanager.dao.MisUserMapper;
import cn.cityre.mis.ifmanager.entity.MisUserPo;
import cn.cityre.mis.ifmanager.service.MisUserService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/9.
 */
@Service(value = "userListService")
public class UserListServiceImpl implements UserListService {
    @Autowired
    private UserListMapper userListMapper;
    @Autowired
    private MisUserService misUserService;

    @Override
    public PaginationResult<UserList> getExistUserInfoList(SearchParams searchParams) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        QueryParams queryParams = searchParams.getQueryParams();
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(), queryParams.getFirstResult(), queryParams.getPageNo());
        Map<String, Object> map = new HashMap<>();
        map.put("pagingCriteria", pagingCriteria);
        if (searchParams.getPayFlag()!=null&&!searchParams.getPayFlag().equals("null")){
            map.put("payFlag",searchParams.getPayFlag());
        }
        if (searchParams.getUid()!=null){
            map.put("uid",searchParams.getUid());
        }
        if (searchParams.getUserType()!=null&&!searchParams.getUserType().equals("null")){
            map.put("userType",searchParams.getUserType());
        }
        if (searchParams.getRegStartTime()!=null){
            map.put("regStartTime",searchParams.getRegStartTime());
        }if (searchParams.getRegEndTime()!=null){
            map.put("regEndTime",searchParams.getRegEndTime());
        }
        if (searchParams.getServiceStartTime()!=null){
            map.put("serviceStartTime",searchParams.getServiceStartTime());
        }
        if (searchParams.getServiceEndTime()!=null){
            map.put("serviceEndTime",searchParams.getServiceEndTime());
        }
        if (searchParams.getNewUser().equals("true")){
            map.put("newUser",true);
        }
        PaginationResult<UserList> paginationResult = null;
        PageMyBatis<UserList> pageMyBatis = userListMapper.selectUserInfoByPage(map);

        paginationResult = PaginationResult.pagination(pageMyBatis, (int) pageMyBatis.getTotal(), queryParams.getPageNo(), queryParams.getPageSize());

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

    @Override
    public List<UserList> getExportList() {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        List<UserList> list = userListMapper.selectExportInfo();
        DataSourceContextHolder.setDbType("dataSource_core");
        return list;
    }
}

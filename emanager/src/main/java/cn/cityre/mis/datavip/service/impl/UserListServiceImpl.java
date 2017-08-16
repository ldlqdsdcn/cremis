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
        if (searchParams.getPayFlag()!=null){
            map.put("payFlag",searchParams.getPayFlag());
        }
        if (searchParams.getUid()!=null){
            map.put("uid",searchParams.getUid());
        }
        if (searchParams.getUserType()!=null){
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
    public List<UserList> getExportList(List<SearchField> searchFields) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        Map<String, Object> map = new HashMap<>();
        if (searchFields == null) {
            map.put("uid", null);
            map.put("userType", null);
            map.put("regStartTime", null);
            map.put("regEndTime", null);
            map.put("payTel", null);
            map.put("payFlag", null);
            map.put("serviceStartTime", null);
            map.put("serviceEndTime", null);
        } else {
            for (SearchField searchField : searchFields) {
                if (searchField.getField().equals("uid")) {
                    map.put("uid", searchField.getValue());
                } else {
                    map.put("uid", null);
                }
                if (searchField.getField().equals("userType")) {
                    map.put("userType", searchField.getValue());
                } else {
                    map.put("userType", null);
                }
                if (searchField.getField().equals("regStartTime")) {
                    map.put("regStartTime", searchField.getValue());
                } else {
                    map.put("regStartTime", null);
                }
                if (searchField.getField().equals("regEndTime")) {
                    map.put("regEndTime", searchField.getValue());
                } else {
                    map.put("regEndTime", null);
                }
                if (searchField.getField().equals("payTel")) {
                    map.put("payTel", searchField.getValue());
                } else {
                    map.put("payTel", null);
                }
                if (searchField.getField().equals("payFlag")) {
                    map.put("payFlag", searchField.getValue());
                } else {
                    map.put("payFlag", null);
                }
                if (searchField.getField().equals("serviceStartTime")) {
                    map.put("serviceStartTime", searchField.getValue());
                } else {
                    map.put("serviceStartTime", null);
                }
                if (searchField.getField().equals("serviceEndTime")) {
                    map.put("serviceEndTime", searchField.getValue());
                } else {
                    map.put("serviceEndTime", null);
                }
            }
        }
        List<UserList> list = userListMapper.selectExportInfo(map);
        DataSourceContextHolder.setDbType("dataSource_core");
        return list;
    }
}

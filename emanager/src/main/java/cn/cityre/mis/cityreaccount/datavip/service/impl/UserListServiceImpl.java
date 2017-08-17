package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.edi.mis.base.util.DataSourceEnum;
import cn.cityre.mis.account.ifmanager.service.MisUserService;
import cn.cityre.mis.cityreaccount.datavip.dao.UserListMapper;
import cn.cityre.mis.cityreaccount.datavip.dto.SearchParams;
import cn.cityre.mis.cityreaccount.datavip.entity.UserList;
import cn.cityre.mis.cityreaccount.datavip.service.UserListService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

        return paginationResult;
    }




    @Override
    public UserList getExistUserListBySuid(String suid) {
        UserList userList = userListMapper.selectBySuid(suid);
        return userList;
    }

    @Override
    public List<UserList> getExportList(SearchParams searchParams) {
        Map<String, Object> map = new HashMap<>();
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

        List<UserList> list = userListMapper.selectExportInfo(map);
        List<UserList> exportList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(UserList userList:list){
            if (userList.getRegTime()!=null){
                userList.setRegTimeString(simpleDateFormat.format(userList.getRegTime()));
            }
            if (userList.getStartTime()!=null){
                userList.setStartTimeString(simpleDateFormat.format(userList.getStartTime()));
            }
            if (userList.getEndTime()!=null){
                userList.setEndTimeString(simpleDateFormat.format(userList.getEndTime()));
            }
            /*用户状态*/
            if (userList.getFlag()!=null){
                if (userList.getFlag()==1){
                    userList.setFlagString("激活");
                }else if(userList.getFlag()==0){
                    userList.setFlagString("未激活");
                }else if (userList.getFlag()==-1){
                    userList.setFlagString("无效");
                }else{
                    userList.setFlagString("");
                }
            }else{
                userList.setFlagString("");
            }
            if (userList.getPayFlag()!=null){
                if (userList.getPayFlag().equals("1")){
                    userList.setPayFlag("已支付");
                }else if (userList.getPayFlag().equals("0")){
                    userList.setPayFlag("未支付");
                }else if(userList.getPayFlag().equals("-1")){
                    userList.setPayFlag("支付失败");
                }
            }
            if (userList.getPLevel()!=null){
                if (userList.getPLevel().equals("1")){
                    userList.setPLevel("小区");
                }else if (userList.getPLevel().equals("2")){
                    userList.setPLevel("行政");
                }else if (userList.getPLevel().equals("3")){
                    userList.setPLevel("城市");
                }else if (userList.getPLevel().equals("4")){
                    userList.setPLevel("附近");
                }else if (userList.getPLevel().equals("5")){
                    userList.setPLevel("省");
                }else if (userList.getPLevel().equals("6")){
                    userList.setPLevel("全国");
                }else {
                    userList.setPLevel("");
                }
            }
            if (userList.getDealType()!=null){
                if (userList.getDealType().equals("1")){
                    userList.setDealType("出售");
                }else if (userList.getDealType().equals("2")){
                    userList.setDealType("出租");
                }else if (userList.getDealType().equals("3")){
                    userList.setDealType("新楼盘");
                }else if (userList.getDealType().equals("4")){
                    userList.setDealType("新楼盘开工在售");
                }else if (userList.getDealType().equals("5")){
                    userList.setDealType("新楼盘已竣工");
                }else if (userList.getDealType().equals("6")){
                    userList.setDealType("新楼盘未售");
                }else {
                    userList.setDealType("");
                }
            }
            exportList.add(userList);
//            if (userList.getPLevel().equals())
        }
        DataSourceContextHolder.setDbType("dataSource_core");
        return exportList;
    }
}

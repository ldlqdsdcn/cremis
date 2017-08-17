package cn.cityre.mis.cityreaccount.datavip.service;

import cn.cityre.mis.cityreaccount.datavip.dto.SearchParams;
import cn.cityre.mis.cityreaccount.datavip.entity.UserList;
import com.dsdl.eidea.core.dto.PaginationResult;

import java.util.List;

/**
 * Created by cityre on 2017/8/9.
 */
public interface UserListService {
    PaginationResult<UserList> getExistUserInfoList(SearchParams searchParams);
    /*根据用户suid获取用户*/
    UserList getExistUserListBySuid(String suid);

    List<UserList> getExportList();

}

package cn.cityre.mis.datavip.service;

import cn.cityre.mis.datavip.entity.UserList;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.SearchFacade;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/9.
 */
public interface UserListService {
    PaginationResult<UserList> getExistUserInfoList(Map<String, Object> map, QueryParams queryParams);
    /*根据用户suid获取用户*/
    UserList getExistUserListBySuid(String suid);

}

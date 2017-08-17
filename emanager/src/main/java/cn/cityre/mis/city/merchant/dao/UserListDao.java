package cn.cityre.mis.city.merchant.dao;

import cn.cityre.mis.city.merchant.entity.po.CoInfo;
import cn.cityre.mis.city.merchant.entity.po.ServiceHa;
import cn.cityre.mis.city.merchant.entity.po.UserList;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface UserListDao {
    List<UserList> selectUserlist(Map<String, Object> map);
    int selectUserlistCount(Map<String, Object> map);
    List<ServiceHa> selectServiceingHa(Map<String, Object> map);
    List<ServiceHa> selectOutDateHa(Map<String, Object> map);
    CoInfo selectCoInfo(String cocode);
    List<UserList> selectUserinfo(String cocode);
    int selectUserinfoCount(String cocode);
    UserList selectUserInfo(String uid);
    Date selectRegtimeFromCenterDb(String uid);
    UserList selectUserlistByUid(String uid);
    int updateUserlist_flag_uid(Map<String, Object> map);
    UserList selectUserlistById(Integer id);
    String selectPermissionByUid(String uid);
    int updatePermissionByUid(Map<String, Object> map);

    int selectUserHouseSourceCnt(String uid);
    int selectCoHouseSourceCnt(String cocode);
}

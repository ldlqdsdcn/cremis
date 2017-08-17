package cn.cityre.mis.city.merchant.dao;


import cn.cityre.mis.city.merchant.entity.po.CoUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface CoUserCheckDao {
    List<CoUser> selectCoUserList(Map<String, Object> map);
    int selectCoUserCount(Map<String, Object> map);
    int updateCoUserAll(Map<String, Object> map);
    int updateCoUserByUid(Map<String, Object> map);
    CoUser selectCoUserById(Integer id);
    CoUser selectCoUserByUid(String uid);

    CoUser selectUser_typeLess2(String cocode);
    int updateUser_typeLess2(String cocode);

    CoUser selectUser_typeBig2(String cocode);
    int updateUser_typeBig2(String cocode);
    int updateCocodeEqualUid(String cocode);

    List<CoUser> selectCouser_remanage(Map<String, String> map);

    int updateNewManage(Map<String, Object> map);
    int updateOldManage(Map<String, Object> map);
}

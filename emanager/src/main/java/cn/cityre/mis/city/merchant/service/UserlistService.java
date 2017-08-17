package cn.cityre.mis.city.merchant.service;


import cn.cityre.mis.city.merchant.entity.po.CoInfo;
import cn.cityre.mis.city.merchant.entity.po.UserList;
import com.dsdl.eidea.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/11.
 */
public interface UserlistService {
    public List<UserList> userlist(String citycode, String uid, String phone, String email, String company, Page pager);
    public CoInfo getCoInfo(String cocode, String citycode) throws Exception;
    public List<UserList> userinfo(String cocode, String citycode, Page pager);
    public UserList serviceHaDetail(Map<String, Object> map);
}

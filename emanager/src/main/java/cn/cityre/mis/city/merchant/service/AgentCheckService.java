package cn.cityre.mis.city.merchant.service;


import cn.cityre.mis.city.merchant.entity.po.CoUser;
import cn.cityre.mis.city.merchant.entity.po.UserList;
import com.dsdl.eidea.util.Page;

import java.util.List;

/**
 * Created by cityre on 2017/7/11.
 */
public interface AgentCheckService {
    public List<CoUser> agentCheck(String citypinyin, String status, String agentName, String agentUid, String subStartDate, String subEndDate, String chkStartDate, String chkEndDate, Page pager);
    public String checkAll(String idStr, int certification, String sysuid);
    public String checkone(String uid, int certification, String sysuid);
   public UserList userinfo(String uid, String citypinyin, String citycode);
}

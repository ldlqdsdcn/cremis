package cn.cityre.mis.city.merchant.service;


import cn.cityre.mis.city.merchant.entity.po.CouserInfo;
import cn.cityre.mis.city.merchant.entity.po.Perdaycount;
import com.dsdl.eidea.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/11.
 */
public interface CouserInfoService {
    public List<CouserInfo> couserInfo(Map<String, Object> map, Page pager);
    public List<Perdaycount> perdaycount(String uid);
    public String userlist_agree(String uid, String sysuid);
    public String userlist_refuse(String uid, String sysuid, String reason);
    public String changetoreguser(String uid);
}

package cn.cityre.mis.merchant.service;


import cn.cityre.mis.merchant.entity.po.Headportrait;
import com.dsdl.eidea.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/11.
 */
public interface HeadportraitService {
    public List<Headportrait> headportrait(Map<String,Object> map,Page pager);
    public String checkone(String id, int flag, String sysuid,String headtype,String citypinyin);
    public String checkAll(String idStr, int certification, String sysuid,String headtype,String citypinyin);


}

package cn.cityre.mis.city.merchant.service;


import cn.cityre.mis.city.merchant.entity.po.CoUser;
import cn.cityre.mis.city.merchant.entity.po.Cocl;
import cn.cityre.mis.city.merchant.entity.po.Crmco;
import cn.cityre.mis.city.merchant.entity.po.Perdaycount;
import com.dsdl.eidea.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/11.
 */
public interface CrmcoService {
   public List<Cocl> getcocl();
   public List<Crmco> list(Map<String, Object> map, Page pager);

   public List<Perdaycount> perdaycount(String cocode);

   public String crmco_agree(String cocode, String couid, String sysuid);

   public String crmco_refuse(String cocode, String reason, String sysuid);

   public   Map<String,Object> crmco_edit(Map<String, Object> param);

   public   Map<String,Object> crmco_delete(Map<String, Object> param);

   public   List<CoUser> getCoManage(Map<String, String> param);

   public   Map<String,Object> crmco_remanage(Map<String, Object> param);

}

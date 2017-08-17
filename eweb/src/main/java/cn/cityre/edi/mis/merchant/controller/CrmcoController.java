/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.merchant.controller;

import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.edi.mis.base.util.CityDataSourceUtil;
import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.merchant.entity.po.CoUser;
import cn.cityre.mis.merchant.entity.po.Cocl;
import cn.cityre.mis.merchant.entity.po.Crmco;
import cn.cityre.mis.merchant.service.CrmcoService;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.core.dto.EasyUIResult;
import com.dsdl.eidea.core.web.controller.BaseController;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.util.EasyUiForm;
import com.dsdl.eidea.util.LikeUtil;
import com.dsdl.eidea.util.Page;
import com.dsdl.eidea.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
*/ @Controller
@RequestMapping("/merchant/crmco")
public class CrmcoController extends BaseController {
@Autowired
private CrmcoService crmcoService;

    @RequiresPermissions("view")
    @RequestMapping(value = "/getcocl", method = RequestMethod.POST)
    @ResponseBody
    public List<Cocl> getcocl(){
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        return crmcoService.getcocl();
    }


    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<Crmco> get(String d1, String source, String certp, String copermission, String cocl, String cert_from,
                                        String cert_to, String time_reg_from, String time_reg_to, String begin_time, String end_time,
                                        String lastlogin_begin_time, String lastlogin_end_time, String couid, String Sconame, String cotel,
                                        EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String city_pinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtil.isNotEmpty(d1)){
            map.put("d1",Integer.parseInt(d1));
        }
        if(StringUtil.isNotEmpty(source)) {
            map.put("source", Integer.parseInt(source));
        }
        if(StringUtil.isNotEmpty(certp)){
            map.put("certp",Integer.parseInt(certp));
        }
        if(StringUtil.isNotEmpty(copermission)){
            map.put("copermission",Integer.parseInt(copermission));
        }
        map.put("cocl", cocl);

        map.put("cert_from", cert_from);
        map.put("cert_to", cert_to);
        map.put("time_reg_from", time_reg_from);
        map.put("time_reg_to", time_reg_to);
        map.put("begin_time", begin_time);
        map.put("end_time", end_time);
        map.put("lastlogin_begin_time", lastlogin_begin_time);
        map.put("lastlogin_end_time", lastlogin_end_time);
        map.put("couid", LikeUtil.getLike(couid));
        map.put("sconame", LikeUtil.getLike(Sconame));
        map.put("cotel", LikeUtil.getLike(cotel));
        map.put("limitStart",pager.getStart());
        map.put("limitEnd",pager.getLimit());
        map.put("city_pinyin",city_pinyin);
        List<Crmco> list =crmcoService.list(map,pager);
        EasyUIResult<Crmco>  result = EasyUIResult.pagination(list,pager);
        return result;
    }



    @RequiresPermissions("update")
    @RequestMapping(value = "/crmco_agree", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> crmco_agree(String cocode,String couid){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = crmcoService.crmco_agree(cocode,couid,sysuid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }

    @RequiresPermissions("update")
    @RequestMapping(value = "/crmco_refuse", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> crmco_refuse(String cocode,String reason){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = crmcoService.crmco_refuse(cocode,reason,sysuid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }

    @RequiresPermissions("update")
    @RequestMapping(value = "/crmco_edit", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> crmco_edit(String coname,String d1,String coaddr,String cobrief,String cowebsite,String coemail,String cotel,String coMob,String cofax,String cocode){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("coname",coname);
        param.put("d1",d1);
        param.put("coaddr",coaddr);
        param.put("cobrief",cobrief);
        param.put("cowebsite",cowebsite);
        param.put("coemail",coemail);
        param.put("cotel",cotel);
        param.put("coMob",coMob);
        param.put("cofax",cofax);
        param.put("sysuid",sysuid);
        param.put("cocode",cocode);
        Map<String,Object> result = crmcoService.crmco_edit(param);
        return result;
    }

    @RequiresPermissions("delete")
    @RequestMapping(value = "/crmco_delete", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> crmco_delete(String cocode){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("sysuid",sysuid);
        param.put("cocode",cocode);
        Map<String,Object> result = crmcoService.crmco_delete(param);
        return result;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/crmco_remanage", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> crmco_remanage(String cocode,String olduid,String newuid){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("sysuid",sysuid);
        param.put("cocode",cocode);
        param.put("olduid",olduid);
        param.put("newuid",newuid);
        Map<String,Object> result = crmcoService.crmco_remanage(param);
        return result;
    }

    @RequiresPermissions("view")
    @RequestMapping(value = "/getCoManage", method = RequestMethod.POST)
    @ResponseBody
    public List<CoUser> getCoManage(String cocode,String couid)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        Map<String,String> map = new HashMap<String,String>();
        map.put("cocode",cocode);
        map.put("couid",couid);
        List<CoUser> list =crmcoService.getCoManage(map);
        return list;
    }
}

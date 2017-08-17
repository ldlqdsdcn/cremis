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
import cn.cityre.mis.merchant.entity.po.Headportrait;
import cn.cityre.mis.merchant.service.HeadportraitService;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.core.dto.EasyUIResult;
import com.dsdl.eidea.core.web.controller.BaseController;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.util.EasyUiForm;
import com.dsdl.eidea.util.LikeUtil;
import com.dsdl.eidea.util.Page;
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
@RequestMapping("/merchant/headportrait")
public class HeadportraitController extends BaseController {
;
@Autowired
private HeadportraitService headportraitService;

    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<Headportrait> get(String uid,String phone,String status,String headType, String startDate, String endDate, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String city_pinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("phone", LikeUtil.getLike(phone));
        if(status != null && !status.equals("")){
            map.put("status",Integer.parseInt(status));
        }
        map.put("uid",uid);
        map.put("headType",headType);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("limitStart",pager.getStart());
        map.put("limitEnd",pager.getLimit());
        map.put("city_pinyin",city_pinyin);
        List<Headportrait> list =headportraitService.headportrait(map,pager);
        EasyUIResult<Headportrait>  result = EasyUIResult.pagination(list,pager);
        return result;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/checkOne", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> checkOne(String id,int flag,String headtype){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = headportraitService.checkone(id,flag,sysuid,headtype,citypinyin);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }

    @RequiresPermissions("update")
    @RequestMapping(value = "/checkAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> checkAll(String idStr, int certification,String headtype){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String uid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = headportraitService.checkAll(idStr,certification,uid,headtype,citypinyin);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }
}

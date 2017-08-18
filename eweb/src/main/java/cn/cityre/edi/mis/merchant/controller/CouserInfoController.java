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
import cn.cityre.mis.city.merchant.entity.po.CouserInfo;
import cn.cityre.mis.city.merchant.service.CouserInfoService;
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
@RequestMapping("/mis/merchant/couserInfo")
public class CouserInfoController extends BaseController {
;
@Autowired
private CouserInfoService couserInfoService;

    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<CouserInfo> get(String usertype, String status, String certp, String permission, String regtimeStartDate, String regtimeEndDate,
                                        String certtimeStartDate, String certtimeEndDate, String logintimeStartDate, String logintimeEndDate, String couseruid,
                                        String username, String phone, String email, String coname, String order, boolean turn,
                                        EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String city_pinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtil.isNotEmpty(usertype)){
            map.put("usertype",Integer.parseInt(usertype));
        }
        if(StringUtil.isNotEmpty(status)) {
            map.put("flag", Integer.parseInt(status));
        }
        if(StringUtil.isNotEmpty(certp)){
            map.put("certp",Integer.parseInt(certp));
        }
        if(StringUtil.isNotEmpty(permission)){
            map.put("permission",Integer.parseInt(permission));
        }
        map.put("regtimeStartDate", regtimeStartDate);
        map.put("regtimeEndDate", regtimeEndDate);
        map.put("certtimeStartDate", certtimeStartDate);
        map.put("certtimeEndDate", certtimeEndDate);
        map.put("logintimeStartDate", logintimeStartDate);
        map.put("logintimeEndDate", logintimeEndDate);
        map.put("couseruid", couseruid);
        map.put("username", LikeUtil.getLike(username));
        map.put("phone", LikeUtil.getLike(phone));
        map.put("email", LikeUtil.getLike(email));
        map.put("coname", LikeUtil.getLike(coname));
        map.put("order",Integer.parseInt(order));
        map.put("turn", turn);
        map.put("limitStart",pager.getStart());
        map.put("limitEnd",pager.getLimit());
        map.put("city_pinyin",city_pinyin);
        List<CouserInfo> list =couserInfoService.couserInfo(map,pager);
        EasyUIResult<CouserInfo>  result = EasyUIResult.pagination(list,pager);
        return result;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/userlist_agree", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> userlist_agree(String uid){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = couserInfoService.userlist_agree(uid,sysuid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/userlist_refuse", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> userlist_refuse(String uid,String reason){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = couserInfoService.userlist_refuse(uid,sysuid,reason);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }

    @RequiresPermissions("update")
    @RequestMapping(value = "/changetoreguser", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> changetoreguser(String uid){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = couserInfoService.changetoreguser(uid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }

}

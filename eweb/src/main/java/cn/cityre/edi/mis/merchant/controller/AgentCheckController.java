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
import cn.cityre.mis.merchant.entity.po.UserList;
import cn.cityre.mis.merchant.service.AgentCheckService;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.core.dto.EasyUIResult;
import com.dsdl.eidea.core.web.controller.BaseController;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.util.EasyUiForm;
import com.dsdl.eidea.util.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
*/ @Controller
@RequestMapping("/merchant/agentCheck")
public class AgentCheckController extends BaseController {
;
@Autowired
private AgentCheckService agentCheckService;

    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<CoUser> get(String status, String agentName, String agentUid, String subStartDate, String subEndDate, String chkStartDate, String chkEndDate, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String city_pinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        List<CoUser> list =agentCheckService.agentCheck(city_pinyin,status,agentName,agentUid,subStartDate,subEndDate,chkStartDate,chkEndDate,pager);
        EasyUIResult<CoUser>  result = EasyUIResult.pagination(list,pager);
        return result;
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    public ModelAndView userInfo(String uid){
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        ModelAndView modelAndView = new ModelAndView("/base/registeredUser/userInfo");
        //公司信息
        UserList userInfo = agentCheckService.userinfo(uid,citypinyin,citycode);
        modelAndView.addObject("userInfo",userInfo);
        return modelAndView;
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView get(String uid)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        ModelAndView modelAndView = new ModelAndView("/base/registeredUser/userInfo");
        UserList u =agentCheckService.userinfo(uid,citypinyin,citycode);
        modelAndView.addObject("userInfo",u);
        return modelAndView;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/checkAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> checkAll(String idStr, int certification){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String uid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = agentCheckService.checkAll(idStr,certification,uid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/checkOne", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> checkOne(String uid,int certification){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = agentCheckService.checkone(uid,certification,sysuid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }
}

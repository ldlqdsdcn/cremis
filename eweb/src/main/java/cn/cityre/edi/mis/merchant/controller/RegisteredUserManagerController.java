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
import cn.cityre.mis.merchant.entity.po.CoInfo;
import cn.cityre.mis.merchant.entity.po.Perdaycount;
import cn.cityre.mis.merchant.entity.po.UserList;
import cn.cityre.mis.merchant.service.CouserInfoService;
import cn.cityre.mis.merchant.service.CrmcoService;
import cn.cityre.mis.merchant.service.UserListService;
import com.dsdl.eidea.core.dto.EasyUIResult;
import com.dsdl.eidea.core.web.controller.BaseController;
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
* Created by 刘大磊 on 2017-06-28 15:47:46.
*/ @Controller
@RequestMapping("/merchant/registeredUser")
public class RegisteredUserManagerController extends BaseController {

    @Autowired
    private UserListService userListService;
    @Autowired
    private CouserInfoService couserInfoService;
    @Autowired
    private CrmcoService crmcoService;
    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<UserList> get(String uid, String phone, String email, String company, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        this.pager = new Page("pagerForm", form);
        List<UserList> list =userListService.userlist(citycode,uid,phone,email,company,pager);
        EasyUIResult<UserList>  result = EasyUIResult.pagination(list,pager);
        return result;
    }

    @RequiresPermissions("view")
    @RequestMapping(value = "/coinfos",method = RequestMethod.GET)
    public ModelAndView coInfo(String cocode)throws Exception{
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        ModelAndView modelAndView = new ModelAndView("/base/registeredUser/coInfo");
        //公司信息
        CoInfo coinfo = userListService.getCoInfo(cocode,citypinyin);
        modelAndView.addObject("coinfo",coinfo);
        return modelAndView;
    }

    @RequiresPermissions("view")
    @RequestMapping(value = "/getCoUser", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<UserList> getCoUser(String cocode, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        List<UserList> userinfo = userListService.userinfo(cocode,citypinyin,pager);
        EasyUIResult<UserList>  result = EasyUIResult.pagination(userinfo,pager);
        return result;
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/serviceHaDetail", method = RequestMethod.POST)
    @ResponseBody
    public UserList serviceHaDetail(String cocode, String uid)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",uid);
        map.put("cocode",cocode);
        UserList result = userListService.serviceHaDetail(map);
        return result;
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/perdaycount", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> perdaycount(String uid)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        List<Perdaycount> list =couserInfoService.perdaycount(uid);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",list);
        return result;
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/perdaycountco", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> perdaycountco(String cocode)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String citypinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        List<Perdaycount> list =crmcoService.perdaycount(cocode);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("rows",list);
        return result;
    }
}

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
import cn.cityre.mis.city.merchant.entity.po.CoCheck;
import cn.cityre.mis.city.merchant.service.CompanyCheckService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
*/ @Controller
@RequestMapping("/merchant/companyCheck")
public class CompanyCheckController extends BaseController {
;
@Autowired
private CompanyCheckService companyCheckService;

    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<CoCheck> get(String status, String companyName, String subStartDate, String subEndDate, String chkStartDate, String chkEndDate, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String city_pinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        List<CoCheck> list =companyCheckService.companyCheck(city_pinyin,status,companyName,subStartDate,subEndDate,chkStartDate,chkEndDate,pager);
        EasyUIResult<CoCheck>  result = EasyUIResult.pagination(list,pager);
        return result;
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
        String result = companyCheckService.checkAll(idStr,certification,uid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }
    @RequiresPermissions("update")
    @RequestMapping(value = "/checkOne", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> checkOne(String cocode,int certification){
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String uid = u.getUsername();
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        String result = companyCheckService.checkone(cocode,certification,uid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        return map;
    }
}

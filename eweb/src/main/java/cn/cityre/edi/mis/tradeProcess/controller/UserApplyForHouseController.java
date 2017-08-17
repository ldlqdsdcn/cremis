/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.tradeProcess.controller;

import cn.cityre.edi.mis.base.entity.cpo.CityPo;
import cn.cityre.edi.mis.base.util.CityDataSourceUtil;
import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.tradeProcess.entity.po.Qd_contract;
import cn.cityre.mis.tradeProcess.entity.po.Qd_contract_history;
import cn.cityre.mis.tradeProcess.service.UserApplyCheckHouseService;
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
@RequestMapping("/process/userApplyForHouse")
public class UserApplyForHouseController extends BaseController {
;
@Autowired
private UserApplyCheckHouseService userApplyCheckHouseService;

    @RequiresPermissions("view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<Qd_contract> get(String startDate, String endDate, String phone, String status, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        String city_pinyin = cityPo.getCityPinYin();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        List<Qd_contract> list =userApplyCheckHouseService.userApplyCheckHouseList(startDate,endDate,phone,status,pager);
        EasyUIResult<Qd_contract>  result = EasyUIResult.pagination(list,pager);
        return result;
    }
    /**
     * 受理银行贷款信息
     * @return
     * @throws Exception
     */
    @RequiresPermissions("update")
    @RequestMapping(value = "/addBankInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addBankInfo(String bankname,String amount,String date,String id,String status,String preBankName,String preAmount,String preDate,String preMisName){
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        userApplyCheckHouseService.addBankInfo(Integer.valueOf(id), bankname, amount, date,Integer.parseInt(status),preBankName,preAmount,preDate,preMisName,sysuid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        return map;
    }

    /**
     * 补充房源地址信息
     * @return
     * @throws Exception
     */
    @RequiresPermissions("update")
    @RequestMapping(value = "/addHouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>  addHouseInfo(String district_dig,String street_dig,String stno_dig,String ha_dig,String bldgno_dig,String unit_dig,String roomno_dig,String id){
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        UserBo u = (UserBo)session.getAttribute(WebConst.SESSION_LOGINUSER);
        String sysuid = u.getUsername();
        userApplyCheckHouseService.addHouseInfo(Integer.valueOf(id), district_dig, street_dig, stno_dig, ha_dig, bldgno_dig, unit_dig, roomno_dig);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        return map;
    }

    @RequiresPermissions("view")
    @RequestMapping(value = "/historylist", method = RequestMethod.POST)
    @ResponseBody
    public EasyUIResult<Qd_contract_history> operationHistoryList(String id, EasyUiForm form)throws Exception {
        CityPo cityPo =(CityPo)session.getAttribute("currentCity");
        String citycode = cityPo.getCityid();
        DataSourceContextHolder.setDbType(CityDataSourceUtil.changeDB(citycode));
        this.pager = new Page("pagerForm", form);
        List<Qd_contract_history> list =userApplyCheckHouseService.historyList(Integer.parseInt(id),pager);
        EasyUIResult<Qd_contract_history>  result = EasyUIResult.pagination(list,pager);
        return result;
    }
}

package cn.cityre.edi.mis.datavip.web.controller;


import cn.cityre.mis.cityreaccount.datavip.entity.Bills;
import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfoHistory;
import cn.cityre.mis.cityreaccount.datavip.service.UserPaymentInfoHistoryService;
import cn.cityre.mis.cityreaccount.datavip.service.UserPaymentInfoService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by cityre on 2017/8/3.
 */
@Controller(value = "userPaymentInfoController")
@RequestMapping(value = "/mis/datavip/userpaymentInfo")
public class UserPaymentInfoController {
    @Autowired
    private UserPaymentInfoService userPaymentInfoService;
    @Autowired
    private UserPaymentInfoHistoryService userPaymentInfoHistoryService;



    @RequiresPermissions("update")
    @ResponseBody
    @RequestMapping(value = "/closeService",method = RequestMethod.POST)
    public JsonResult<Bills> closeService(HttpSession httpSession, @RequestBody Bills bills){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        userPaymentInfoService.closeServiceByBillCode(bills.getBillCode());
        UserPaymentInfo userPaymentInfo = userPaymentInfoService.getExistPaymentInfo(bills.getBillCode());
        return JsonResult.success(bills);
    }
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @RequiresPermissions(value = "add")
    public JsonResult<UserPaymentInfoHistory> create(){
        UserPaymentInfoHistory userPaymentInfoHistory = userPaymentInfoHistoryService.getExistPaymentHistoryByByPrimaryKey(14);
        userPaymentInfoHistoryService.createPaymentInfoHistory(userPaymentInfoHistory);
        return  JsonResult.success(userPaymentInfoHistory);
    }

}

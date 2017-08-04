package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.datavip.service.UserPaymentInfoService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by cityre on 2017/8/3.
 */
@Controller(value = "userPaymentInfoController")
@RequestMapping(value = "/base/userpaymentInfo")
public class UserPaymentInfoController {
    @Autowired
    private UserPaymentInfoService userPaymentInfoService;

    @RequestMapping(value = "/getExistPaymentInfo",method = RequestMethod.GET)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<UserPaymentInfo> getExistPaymentInfo(HttpSession httpSession,String billCode){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        UserPaymentInfo userPaymentInfo = userPaymentInfoService.getExistPaymentInfo(billCode);
        if (userPaymentInfo==null){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage(""));
        }
        return JsonResult.success(userPaymentInfo);
    }

    @RequiresPermissions("update")
    @ResponseBody
    @RequestMapping(value = "/closeService",method = RequestMethod.GET)
    public JsonResult<UserPaymentInfo> closeService(HttpSession httpSession,String billCode){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        userPaymentInfoService.closeServiceByBillCode(billCode);
        UserPaymentInfo userPaymentInfo = userPaymentInfoService.getExistPaymentInfo(billCode);
        return JsonResult.success(userPaymentInfo);
    }
}

package cn.cityre.edi.mis.datavip.web.controller;


import cn.cityre.mis.account.ifmanager.service.MisUserService;
import cn.cityre.mis.cityreaccount.datavip.Excel.InvoiceTypeHeader;
import cn.cityre.mis.cityreaccount.datavip.del.BillFlagDef;
import cn.cityre.mis.cityreaccount.datavip.dto.SearchParams;
import cn.cityre.mis.cityreaccount.datavip.entity.DicUserType;
import cn.cityre.mis.cityreaccount.datavip.entity.UserList;
import cn.cityre.mis.cityreaccount.datavip.service.DicUserTypeService;
import cn.cityre.mis.cityreaccount.datavip.service.UserListService;
import cn.cityre.mis.cityreaccount.datavip.util.ExlUtil;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/9.
 */
@Controller(value = "userListController")
@RequestMapping(value = "/mis/datavip/new")
public class UserListController {
    private static final String URL = "mis_userlist";

    @Autowired
    private UserListService userListService;
    @Autowired
    private MisUserService misUserService;
    @Autowired
    private DicUserTypeService dicUserTypeService;

    @RequestMapping(value = "/showList")
    @RequiresPermissions(value = "view")
    @ResponseBody
    public ModelAndView showList(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/mis/datavip/new/vipinfo");
        modelAndView.addObject(WebConst.PAGE_URI, URL);
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiresPermissions("view")
    public JsonResult<PaginationResult<UserList>> list(HttpSession httpSession, @RequestBody SearchParams searchParams) {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        PaginationResult<UserList> paginationResult = null;
        paginationResult = userListService.getExistUserInfoList(searchParams);
        if (paginationResult.getData()==null){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage("mis.datavip.error.noexist"));
        }
        return JsonResult.success(paginationResult);
    }


    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @RequiresPermissions("view")
    public JsonResult<UserList> get(HttpSession httpSession, String suid) {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        return JsonResult.success(userListService.getExistUserListBySuid(suid));
    }

    //获取用户类型
    @ResponseBody
    @RequestMapping(value = "/getUserType", method = RequestMethod.GET)
    public JsonResult<List<DicUserType>> getUserType() {
        List<DicUserType>list = dicUserTypeService.getExistUserTypeList();
        list.add(null);
        return JsonResult.success(list);
    }

    //获取账单状态
    @ResponseBody
    @RequestMapping(value = "/getBillsFlag", method = RequestMethod.GET)
    public JsonResult<String> getBillsFlag() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (BillFlagDef billFlagDef : BillFlagDef.values()) {
            JsonObject list = new JsonObject();
            list.addProperty("key", billFlagDef.getKey());
            list.addProperty("value", billFlagDef.getValue());
            jsonArray.add(list);
        }
        jsonObject.add("billsFlag", jsonArray);
        return JsonResult.success(jsonObject.toString());
    }

    @RequestMapping(value = "/exportExl",method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportExl(UserList userList, Model model, HttpServletRequest httpRequest) throws IOException, ServletException {
        String uid=httpRequest.getParameter("uid");
        String userType=httpRequest.getParameter("userType");
        String payTel=httpRequest.getParameter("payTel");
        String payFlag=httpRequest.getParameter("payFlag");
        String newUser=httpRequest.getParameter("newUser");
        String regStartTime=httpRequest.getParameter("regStartTime");
        String regEndTime=httpRequest.getParameter("regEndTime");
        String serviceStartTime=httpRequest.getParameter("serviceStartTime");
        String serviceEndTime=httpRequest.getParameter("serviceEndTime");
        Map<String,Object> map = new HashMap<>();
        if (!uid.equals("null")) {
            map.put("uid", uid);
        }
        if (!userType.equals("null")){
            map.put("userType",userType);
        }
        if (!payTel.equals("null")){
            map.put("payTel",payTel);
        }
        if (!payFlag.equals("null")){
            map.put("payFlag",payFlag);
        }
        if (!newUser.equals("null")){
            map.put("newUser",newUser);
        }
        if (!regStartTime.equals("null")){
            map.put("regStartTime",regStartTime);
        }
        if (!regEndTime.equals("null")){
            map.put("regEndTime",regEndTime);
        }
        if (!serviceStartTime.equals("null")){
            map.put("serviceStartTime",serviceStartTime);
        }
        if (!serviceEndTime.equals("null")){
            map.put("serviceEndTime",serviceEndTime);
        }
        List<UserList> userLists = userListService.getExportList(map);

        return ExlUtil.getDataStream(new InvoiceTypeHeader(),userLists, "数据会员开票信息");
    }
}

package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.datavip.del.BillFlagDef;
import cn.cityre.mis.datavip.dto.SearchParams;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.entity.DicUserType;
import cn.cityre.mis.datavip.entity.UserList;
import cn.cityre.mis.datavip.service.DicUserTypeService;
import cn.cityre.mis.datavip.service.UserListService;
import cn.cityre.mis.datavip.util.CityreExcel;
import cn.cityre.mis.datavip.util.ExcelExport;
import cn.cityre.mis.ifmanager.entity.MisUserPo;
import cn.cityre.mis.ifmanager.service.MisUserService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.scene.control.Pagination;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
        QueryParams queryParams = searchParams.getQueryParams();
        if (searchParams.getNewUser().equals("true")) {
            List<UserList> userLists = new ArrayList<>();
            List<MisUserPo> misUserList = misUserService.getNewUser();
            for (MisUserPo misUserPo : misUserList) {
                for (UserList userList : paginationResult.getData()) {
                    if (userList.getUid().equals(misUserPo.getUserId())) {
                        userLists.add(userList);
                    }
                }
            }
            paginationResult = PaginationResult.pagination(userLists, userLists.size(), queryParams.getPageNo(), queryParams.getPageSize());
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
        jsonArray.add(null);
        jsonObject.add("billsFlag", jsonArray);
        return JsonResult.success(jsonObject.toString());
    }

    @RequiresPermissions("view")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> exportExcel(HttpSession httpSession, @RequestBody List<UserList> userLists) throws IOException {
        List<SearchField> searchFields = SearchFieldHelper.getSearchField(URL, httpSession);
        List<String> headList = new ArrayList<>();
        headList.add("用户登录名");
        headList.add("姓名");
        headList.add("用户类型");
        headList.add("注册时间");
        headList.add("电话");
        headList.add("邮箱");
        headList.add("用户状态");
        headList.add("账单号");
        headList.add("账单状态");
        headList.add("账单金额");
        headList.add("服务类型");
        headList.add("城市");
        headList.add("查询级别");
        headList.add("名称");
        headList.add("附近");
        headList.add("租售状态");
        headList.add("开始时间");
        headList.add("结束时间");
        headList.add("发票头");
        Map<String, Integer> dataMap = new LinkedHashMap<>();
        dataMap.put("uid", 2);
        dataMap.put("userName", 2);
        dataMap.put("userTypeName", 2);
        dataMap.put("regTime", 2);
        dataMap.put("payTel", 2);
        dataMap.put("email", 2);
        dataMap.put("flag", 2);
        dataMap.put("billCode", 2);
        dataMap.put("payFlag", 2);
        dataMap.put("payAmount", 2);
        dataMap.put("note", 2);
        dataMap.put("billsCityCode", 2);
        dataMap.put("pLevel", 2);
        dataMap.put("contentName", 2);
        dataMap.put("pGps", 2);
        dataMap.put("invoiceBankNo", 2);
        dataMap.put("dealType", 2);
        dataMap.put("startTime", 2);
        dataMap.put("endTime", 2);
        dataMap.put("invoiceTitle", 2);
        ExcelExport excelExport = new CityreExcel("VipInfo", headList);
        List<UserList> dataList = userListService.getExportList(searchFields);
        List<UserList> userData = new ArrayList<>();
        for (SearchField searchField : searchFields) {
            if (searchField.getField().equals("newUser") && searchField.getValue() != null) {
                List<MisUserPo> misUserList = misUserService.getNewUser();
                for (MisUserPo misUserPo : misUserList) {
                    for (UserList userList : dataList) {
                        if (userList.getUid().equals(misUserPo.getUserId())) {
                            userData.add(userList);
                        }
                    }
                }
            } else {
                userData = dataList;
            }
        }
        excelExport.setDataList(userData, dataMap, 2, true);
        excelExport.writeFile("C:/VipInfo.xlsx");
        return JsonResult.success("C:/VipInfo.xlsx");
    }

}

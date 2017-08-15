package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.entity.UserList;
import cn.cityre.mis.datavip.service.UserListService;
import cn.cityre.mis.datavip.util.CityreExcel;
import cn.cityre.mis.datavip.util.ExcelExport;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
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
    public JsonResult<PaginationResult<UserList>> list(HttpSession httpSession, @RequestBody QueryParams queryParams) {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        List<SearchField> searchFields = SearchFieldHelper.getSearchField(URL, httpSession);
        if (searchFields == null || searchFields.size() == 0) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), userResource.getMessage(""));
        }
        PaginationResult<UserList> paginationResult = userListService.getExistUserInfoList(searchFields, queryParams);
        return JsonResult.success(paginationResult);
    }

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public JsonResult<UserList> get(HttpSession httpSession,String suid){
        UserResource userResource=(UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        return JsonResult.success(userListService.getExistUserListBySuid(suid));
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> exportExcel(HttpSession httpSession, @RequestBody List<UserList> userLists) throws IOException {
        List<SearchField> searchFields = SearchFieldHelper.getSearchField(URL,httpSession);
        List<String> headList = new ArrayList<>();
        headList.add("用户登录名");
        headList.add("大订单号");
        headList.add("订单号");
        headList.add("支付宝账号");
        headList.add("开始时间");
        headList.add("结束时间");
        headList.add("支付类型");
        headList.add("支付金额");
        headList.add("支付状态");
        headList.add("支付时间");
        headList.add("是否邮寄(1-》是；0-》否");
        headList.add("发票类型");
        headList.add("发票抬头");
        headList.add("纳税人识别号");
        headList.add("地址电话");
        headList.add("开户行及账号");
        headList.add("开始时间");
        headList.add("结束时间");
        headList.add("发票头");
        Map<String,Integer> dataMap= new LinkedHashMap<>();
        dataMap.put("uid",2);
        dataMap.put("bigBillCode",2);
        dataMap.put("billCode",2);
        dataMap.put("alipayBillCode",2);
        dataMap.put("startTime",2);
        dataMap.put("endTime",2);
        dataMap.put("wPayType",2);
        dataMap.put("productCost",2);
        dataMap.put("payFlag",2);
        dataMap.put("payUpdateTime",2);
        dataMap.put("postInvoiceFlag",2);
        dataMap.put("invoiceType",2);
        dataMap.put("invoiceTitle",2);
        dataMap.put("invoiceTaxNo",2);
        dataMap.put("invoiceAdTel",2);
        dataMap.put("invoiceBankNo",2);
        dataMap.put("postUser",2);
        dataMap.put("typeName",2);
        dataMap.put("address",2);
        dataMap.put("tel",2);
        dataMap.put("wPayUser",2);
        dataMap.put("wPayTel",2);
        ExcelExport excelExport = new CityreExcel("VipInfo",headList);
        List<UserList> dataList=userListService.getExportList(searchFields);
        excelExport.setDataList(dataList,dataMap,2,true);
        excelExport.writeFile("F:/VipInfo.xlsx");
        return JsonResult.success("F:/VipInfo.xlsx");
    }

}

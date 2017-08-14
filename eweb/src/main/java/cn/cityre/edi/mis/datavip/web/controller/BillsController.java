package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.datavip.service.BillsService;
import cn.cityre.mis.datavip.service.UserPaymentInfoService;
import cn.cityre.mis.datavip.util.CityreExcel;
import cn.cityre.mis.datavip.util.ExcelExport;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.groovy.util.ListHashMap;
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
import java.text.ParseException;
import java.util.*;

/**
 * Created by cityre on 2017/8/2.
 */
@Controller
@RequestMapping(value = "/mis/datavip/bills")
public class BillsController {
    private static final String URL = "bills";

    @Autowired
    private BillsService billsService;
    @Autowired
    private UserPaymentInfoService userPaymentInfoService;

    @RequestMapping(value = "/showList", method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("mis/datavip/bills/bills");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI, URL);
        return modelAndView;
    }

    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PaginationResult<Bills>> list(HttpSession httpSession, @RequestBody QueryParams queryParams) throws ParseException {
        List<SearchField> searchFields = SearchFieldHelper.getSearchField(URL,httpSession);
        PaginationResult<Bills> paginationResult = billsService.getBillsListByOthers(searchFields,queryParams);
        return JsonResult.success(paginationResult);
    }

    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Bills> get(HttpSession httpSession, Integer id) {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        Bills bills = billsService.getExistBillsById(id);
        return JsonResult.success(bills);
    }

    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/getBills", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Bills> getBills(HttpSession httpSession, String billCode) throws ParseException {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        Bills bills = billsService.getExistBillsByCode(billCode);
        return JsonResult.success(bills);
    }

    @RequiresPermissions(value = "update")
    @RequestMapping(value = "/addInvoice", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Bills> addInvoice(HttpSession httpSession, @RequestBody Bills bills) {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        billsService.addInvoice(bills);
        return JsonResult.success(bills);
    }

    @RequestMapping(value = "/openService", method = RequestMethod.POST)
    @RequiresPermissions(value = "update")
    @ResponseBody
    public JsonResult<Bills> openService(HttpSession httpSession,@RequestBody Bills bills) throws ParseException {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        billsService.openService(bills);
        return JsonResult.success(bills);
    }
    @RequestMapping(value = "/getUserPaymentInfo", method = RequestMethod.GET)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<UserPaymentInfo> getExistPaymentInfo(HttpSession httpSession,String billCode) {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        UserPaymentInfo userPaymentInfo = userPaymentInfoService.getExistPaymentInfo(billCode);
        if (userPaymentInfo == null) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), userResource.getMessage(""));
        }
        return JsonResult.success(userPaymentInfo);
    }
    @RequiresPermissions("view")
    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST)
    @ResponseBody
    public void exportExcel(HttpSession httpSession, @RequestBody List<Bills> bills) throws IOException {
        List<String> headList = new ArrayList<>();
        headList.add("用户名");
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
        headList.add("收件人");
        headList.add("收件类型");
        headList.add("发送地址");
        headList.add("联系电话");
        headList.add("支付人");
        headList.add("支付电话");
        headList.add("发票号");
        headList.add("开票日期");
        headList.add("用户类型");
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
        dataMap.put("invoiceNo",2);
        dataMap.put("kpInvoiceTime",2);
        dataMap.put("userTypeName",2);
        ExcelExport excelExport = new CityreExcel("BillsInfo",headList);
        excelExport.setDataList(bills,dataMap,2,true);
        excelExport.writeFile("F:/BillsInfo.xlsx");
    }
}

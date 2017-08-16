package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.datavip.del.BillFlagDef;
import cn.cityre.mis.datavip.del.InvoiceFlag;
import cn.cityre.mis.datavip.del.InvoiceState;
import cn.cityre.mis.datavip.del.InvoiceType;
import cn.cityre.mis.datavip.dto.SearchBillParams;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.entity.DicPayType;
import cn.cityre.mis.datavip.entity.DicPostType;
import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.datavip.service.BillsService;
import cn.cityre.mis.datavip.service.DicPayTypeService;
import cn.cityre.mis.datavip.service.DicPostTypeService;
import cn.cityre.mis.datavip.service.UserPaymentInfoService;
import cn.cityre.mis.datavip.util.CityreExcel;
import cn.cityre.mis.datavip.util.ExcelExport;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.base.entity.po.UserPo;
import com.dsdl.eidea.base.service.UserService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cryptography.AesUtil;
import cryptography.Md5Util;
import cryptography.RsaUtil;
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
    private static final String SPLIT_FOR_USERNAME_AND_PASSWORD = "\\|";

    @Autowired
    private BillsService billsService;
    @Autowired
    private UserPaymentInfoService userPaymentInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private DicPayTypeService dicPayTypeService;
    @Autowired
    private DicPostTypeService dicPostTypeService;

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
    public JsonResult<PaginationResult<Bills>> list(HttpSession httpSession, @RequestBody SearchBillParams searchBillParams) throws ParseException {
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        PaginationResult<Bills> paginationResult = billsService.getBillsListByOthers(searchBillParams);
        if (paginationResult.getData()==null||paginationResult.getData().size()==0){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage("mis.datavip.error.noexist"));
        }
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
    @ResponseBody
    @RequestMapping(value = "/confirmPassword",method = RequestMethod.POST)
    public JsonResult<Boolean> confirmPassword(HttpSession httpSession,@RequestBody String password){
        UserBo userBo = (UserBo)httpSession.getAttribute(WebConst.SESSION_LOGINUSER);
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        UserBo user = userService.getUser(userBo.getId());
        //去掉无用的前缀"{allparam:"和后缀"}"
        String param = password.substring(13,password.length()-1);
        //提取出cipherUsernameAndPassword、enkey和iv
        String[] str = param.split(SPLIT_FOR_USERNAME_AND_PASSWORD);
        String cipherUsernameAndPassword = str[0];
        String enkey = str[1];
        String iv = str[2];
        //对加密后的用户名和密码进行解密
        AesUtil aesUtil = new AesUtil();
        RsaUtil rsaUtil = new RsaUtil();
        Md5Util md5Util = new Md5Util();
        String dec = rsaUtil.rsaDecode(enkey);
        String decodeContent = aesUtil.aesDecode(dec ,iv , cipherUsernameAndPassword);
        //对解密后的字符串进行拆解，还原出username和password
        String[] cipherstr = decodeContent.split(SPLIT_FOR_USERNAME_AND_PASSWORD);
        String username = cipherstr[0];
        String userpassword = cipherstr[1];
        //再用MD5进行加密,与数据库中的内容进行比对
        String md5Password = md5Util.EncoderByMd5(userpassword).toLowerCase();
        System.out.println(md5Password);
        if (user.getPassword().equals(md5Password)){
            return JsonResult.success(true);
        }else{
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage("password.confirm.password.inconsistent"));
        }

    }

    @RequestMapping(value = "/openService", method = RequestMethod.POST)
    @RequiresPermissions(value = "update")
    @ResponseBody
    public JsonResult<Bills> openService(HttpSession httpSession,@RequestBody Bills bills) throws ParseException {
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        if (bills.getUserPaymentInfo()==null){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage("error.mis.bills.payTime.empty"));
        }
        billsService.openService(bills);
        return JsonResult.success(bills);
    }

    /*获取支付类型*/
    @RequestMapping(value = "/getPayType",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<List<DicPayType>> getPayType(){
        return JsonResult.success(dicPayTypeService.getExistAllType());
    }
    /*获取邮寄类型*/
    @RequestMapping(value = "/getPostType",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<List<DicPostType>> getPostType(){
        return JsonResult.success(dicPostTypeService.getExistPostTypeList());
    }
    /*获取支付状态*/
    @RequestMapping(value="/getBillFlag",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<String> getBillFlag(){
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(BillFlagDef billFlagDef:BillFlagDef.values()){
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("key",billFlagDef.getKey());
            jsonObject1.addProperty("value",billFlagDef.getValue());
            jsonArray.add(jsonObject1);
        }
        jsonObject.add("billFlagList",jsonArray);
        return JsonResult.success(jsonObject.toString());
    }
    /*获取状态*/
    @RequestMapping(value="/getFlag",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<String> getFlag(){
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(InvoiceFlag invoiceFlag:InvoiceFlag.values()){
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("key",invoiceFlag.getKey());
            jsonObject1.addProperty("value",invoiceFlag.getValue());
            jsonArray.add(jsonObject1);
        }
        jsonObject.add("flag",jsonArray);
        return JsonResult.success(jsonObject.toString());
    }
    /*获取状态*/
    @RequestMapping(value="/getInvoiceState",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<String> getInvoiceState(){
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(InvoiceState invoiceState:InvoiceState.values()){
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("key",invoiceState.getKey());
            jsonObject1.addProperty("value",invoiceState.getValue());
            jsonArray.add(jsonObject1);
        }
        jsonObject.add("invoiceState",jsonArray);
        return JsonResult.success(jsonObject.toString());
    }
    /*获取发票状态*/
    @RequestMapping(value="/getInvoiceFlag",method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<String> getInvoiceFlag(){
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(InvoiceType invoiceType:InvoiceType.values()){
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("key",invoiceType.getKey());
            jsonObject1.addProperty("value",invoiceType.getValue());
            jsonArray.add(jsonObject1);
        }
        jsonObject.add("invoiceTypeList",jsonArray);
        return JsonResult.success(jsonObject.toString());
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
//    @RequiresPermissions("view")
//    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult<String> exportExcel(HttpSession httpSession, @RequestBody List<Bills> bills) throws IOException {
//        List<SearchField> searchFields = SearchFieldHelper.getSearchField(URL,httpSession);
//        List<String> headList = new ArrayList<>();
//        headList.add("用户名");
//        headList.add("大订单号");
//        headList.add("订单号");
//        headList.add("支付宝账号");
//        headList.add("开始时间");
//        headList.add("结束时间");
//        headList.add("支付类型");
//        headList.add("支付金额");
//        headList.add("支付状态");
//        headList.add("支付时间");
//        headList.add("是否邮寄(1-》是；0-》否");
//        headList.add("发票类型");
//        headList.add("发票抬头");
//        headList.add("纳税人识别号");
//        headList.add("地址电话");
//        headList.add("开户行及账号");
//        headList.add("收件人");
//        headList.add("收件类型");
//        headList.add("发送地址");
//        headList.add("联系电话");
//        headList.add("支付人");
//        headList.add("支付电话");
//        headList.add("发票号");
//        headList.add("开票日期");
//        headList.add("用户类型");
//        Map<String,Integer> dataMap= new LinkedHashMap<>();
//        dataMap.put("uid",2);
//        dataMap.put("bigBillCode",2);
//        dataMap.put("billCode",2);
//        dataMap.put("alipayBillCode",2);
//        dataMap.put("startTime",2);
//        dataMap.put("endTime",2);
//        dataMap.put("wPayType",2);
//        dataMap.put("productCost",2);
//        dataMap.put("payFlag",2);
//        dataMap.put("payUpdateTime",2);
//        dataMap.put("postInvoiceFlag",2);
//        dataMap.put("invoiceType",2);
//        dataMap.put("invoiceTitle",2);
//        dataMap.put("invoiceTaxNo",2);
//        dataMap.put("invoiceAdTel",2);
//        dataMap.put("invoiceBankNo",2);
//        dataMap.put("postUser",2);
//        dataMap.put("typeName",2);
//        dataMap.put("address",2);
//        dataMap.put("tel",2);
//        dataMap.put("wPayUser",2);
//        dataMap.put("wPayTel",2);
//        dataMap.put("invoiceNo",2);
//        dataMap.put("kpInvoiceTime",2);
//        dataMap.put("userTypeName",2);
//        List<Bills> dataList = billsService.getExportList(searchFields);
//        ExcelExport excelExport = new CityreExcel("BillsInfo",headList);
//        excelExport.setDataList(dataList,dataMap,2,true);
//        excelExport.writeFile("F:/BillsInfo.xlsx");
//        return JsonResult.success("C:/VipInfo.xlsx");
//
//    }
}

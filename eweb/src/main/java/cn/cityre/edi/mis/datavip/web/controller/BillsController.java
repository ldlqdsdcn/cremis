package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.service.BillsService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * Created by cityre on 2017/8/2.
 */
@Controller
@RequestMapping(value = "/mis/bills")
public class BillsController {
    private static final String URL = "bills";

    @Autowired
    private BillsService billsService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/mis/bills/bills");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }

    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PaginationResult<Bills>> list(HttpSession httpSession,@RequestBody QueryParams queryParams){

        PaginationResult<Bills> paginationResult = billsService.getBillsListByOthers(queryParams);
        return JsonResult.success(paginationResult);
    }

    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Bills> get(HttpSession httpSession,Integer id){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        Bills bills = billsService.getExistBillsById(id);
        return JsonResult.success(bills);
    }

    @RequiresPermissions(value = "update")
    @RequestMapping(value = "/addInvoice",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Bills> addInvoice(HttpSession httpSession,@RequestBody Bills bills){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        billsService.addInvoice(bills);
        return JsonResult.success(bills);
    }

    @RequestMapping(value = "/openService",method = RequestMethod.POST)
    @RequiresPermissions(value = "update")
    @ResponseBody
    public JsonResult<Bills> openService(HttpSession httpSession,@RequestBody Bills bills) throws ParseException {
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        billsService.openService(bills);
        return JsonResult.success(bills);
    }
}

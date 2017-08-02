package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.service.BillsService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cityre on 2017/8/2.
 */
@Controller
@RequestMapping(value = "/base/bills")
public class BillsController {
    private static final String Url = "mis_bills";

    @Autowired
    private BillsService billsService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/bills/bills");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,Url);
        return modelAndView;
    }
    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PaginationResult<Bills>> list(@RequestBody QueryParams queryParams){
        PaginationResult<Bills> paginationResult = billsService.getBillsListByOthers(queryParams);
        return JsonResult.success(paginationResult);
    }
}

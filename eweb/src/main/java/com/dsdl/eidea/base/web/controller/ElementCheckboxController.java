package com.dsdl.eidea.base.web.controller;

import com.dsdl.eidea.base.entity.po.ElementCheckboxPo;
import com.dsdl.eidea.base.service.ElementCheckboxService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.googlecode.genericdao.search.Search;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by 车东明 on 2017/6/1.
 */
@Controller
@RequestMapping(value = "/base/elementCheckbox")
public class ElementCheckboxController {
    private static final String URL="core_element_checkbox";
    @Autowired
    private ElementCheckboxService elementCheckboxService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/elementCheckbox/elementCheckbox");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("view")
    public JsonResult<PaginationResult<ElementCheckboxPo>> list(HttpSession httpSession , @RequestBody ItemParams<Integer> itemParams){
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<ElementCheckboxPo> paginationResult = elementCheckboxService.getElementCheckboxListByPaging(search,itemParams);
        return JsonResult.success(paginationResult);
    }
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    @ResponseBody
    public JsonResult<ElementCheckboxPo> get(Integer id){
        ElementCheckboxPo elementCheckboxPo = elementCheckboxService.getElementCheckbox(id);
        return JsonResult.success(elementCheckboxPo);
    }
    @RequiresPermissions(value = "add")
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public JsonResult<ElementCheckboxPo> create(){
        ElementCheckboxPo elementCheckboxPo = new ElementCheckboxPo();
        return JsonResult.success(elementCheckboxPo);
    }
    @RequiresPermissions(value = "add")
    @ResponseBody
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    public JsonResult<ElementCheckboxPo> saveForCreated(@Validated @RequestBody ElementCheckboxPo elementCheckboxPo){
        elementCheckboxService.saveElementCheckbox(elementCheckboxPo);
        return JsonResult.success(elementCheckboxPo);
    }
    @ResponseBody
    @RequiresPermissions("add")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<ElementCheckboxPo> saveForUpdated(HttpSession httpSession,@Validated @RequestBody ElementCheckboxPo elementCheckboxPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        if (elementCheckboxPo.getId()==null){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage(""));
        }
        elementCheckboxService.saveElementCheckbox(elementCheckboxPo);
        return JsonResult.success(elementCheckboxPo);
    }
    @ResponseBody
    @RequiresPermissions("delete")
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    public JsonResult<PaginationResult<ElementCheckboxPo>> deleted(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource =(UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        elementCheckboxService.deletes(deleteParams.getIds());
        ItemParams<Integer> itemParams = new ItemParams<>();
        itemParams.setItemPK(elementCheckboxService.getElementCheckbox(deleteParams.getIds()[0]).getId());
        itemParams.setQueryParams(deleteParams.getQueryParams());
        return list(httpSession,itemParams);
    }
}

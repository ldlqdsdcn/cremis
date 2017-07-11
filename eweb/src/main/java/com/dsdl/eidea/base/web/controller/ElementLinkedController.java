package com.dsdl.eidea.base.web.controller;

import com.dsdl.eidea.base.entity.po.ElementLinkedPo;
import com.dsdl.eidea.base.service.ElementLinkedService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
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
@RequestMapping(value = "/base/elementLinked")
public class ElementLinkedController {
    private static final String URL="core_element_linked";
    @Autowired
    private ElementLinkedService elementLinkedService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/elementLinked/elementLinked");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<PaginationResult<ElementLinkedPo>> list(HttpSession httpSession, @Validated @RequestBody ItemParams<Integer> itemParams){
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<ElementLinkedPo> paginationResult=elementLinkedService.getElementLinkedList(search,itemParams);
        return JsonResult.success(paginationResult);
    }
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<ElementLinkedPo> get(Integer id){
        ElementLinkedPo elementLinkedPo = elementLinkedService.getElementLinkedPo(id);
        return JsonResult.success(elementLinkedPo);
    }
    @RequiresPermissions("add")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<ElementLinkedPo> create(){
        ElementLinkedPo elementLinkedPo = new ElementLinkedPo();
        return JsonResult.success(elementLinkedPo);
    }
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("add")
    public JsonResult<ElementLinkedPo> saveForCreated(HttpSession httpSession, @Validated @RequestBody ElementLinkedPo elementLinkedPo){
        elementLinkedService.saveElementLinkedPo(elementLinkedPo);
        return JsonResult.success(elementLinkedPo);
    }
    @RequiresPermissions("update")
    @ResponseBody
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<ElementLinkedPo> saveForUpdated(HttpSession httpSession,@Validated @RequestBody ElementLinkedPo elementLinkedPo){
        elementLinkedService.saveElementLinkedPo(elementLinkedPo);
        return JsonResult.success(elementLinkedPo);
    }
    @RequiresPermissions("delete")
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PaginationResult<ElementLinkedPo>> delete(HttpSession httpSession, @Validated DeleteParams<Integer> deleteParams){
        elementLinkedService.deleteElementLinkedPo(deleteParams.getIds());
        ItemParams<Integer> itemParams = new ItemParams<>();
        itemParams.setQueryParams(deleteParams.getQueryParams());
        itemParams.setItemPK(elementLinkedService.getElementLinkedPo(deleteParams.getIds()[0]).getElementId());
        return list(httpSession,itemParams);
    }
}

package com.dsdl.eidea.base.web.controller;

import com.dsdl.eidea.base.entity.po.ElementSelectPo;
import com.dsdl.eidea.base.service.ElementSelectService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.def.JavaDataType;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
 * Created by 车东明 on 2017/5/27.
 */
@Controller
@RequestMapping(value = "/base/elementSelect")
public class ElementSelectController {
    private static final String URL="core_element_select";
    @Autowired
    private ElementSelectService elementSelectService;

    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/elementSelect/elementSelect");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @RequiresPermissions(value = "view")
    @ResponseBody
    public JsonResult<PaginationResult<ElementSelectPo>> list(HttpSession httpSession,@RequestBody ItemParams<Integer> itemParams){
        itemParams.setQueryParams(new QueryParams());
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<ElementSelectPo> paginationResult = elementSelectService.getElementSelectListByPaging(search,itemParams);
        return JsonResult.success(paginationResult);
    }
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    @ResponseBody
    public JsonResult<ElementSelectPo> get(Integer id){
        ElementSelectPo elementSelectPo = elementSelectService.getElementSelect(id);
        return JsonResult.success(elementSelectPo);
    }
    @RequiresPermissions(value = "add")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<ElementSelectPo> create(){
        ElementSelectPo elementSelectPo = new ElementSelectPo();
        return JsonResult.success(elementSelectPo);
    }
    @RequiresPermissions(value = "add")
    @ResponseBody
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    public JsonResult<ElementSelectPo> saveForCreated(@Validated @RequestBody ElementSelectPo elementSelectPo){
        elementSelectService.saveElementSelect(elementSelectPo);
        return JsonResult.success(elementSelectPo);
    }
    @RequiresPermissions(value = "update")
    @ResponseBody
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<ElementSelectPo> saveForUpdated(@Validated @RequestBody ElementSelectPo elementSelectPo,HttpSession httpSession){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        if (elementSelectPo.getId()==null){
            JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage(""));
        }
        elementSelectService.saveElementSelect(elementSelectPo);
        return JsonResult.success(elementSelectPo);
    }
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    @RequiresPermissions(value = "delete")
    @ResponseBody
    public JsonResult<PaginationResult<ElementSelectPo>> delete(@Validated @RequestBody DeleteParams<Integer> deleteParams,HttpSession httpSession){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        if (deleteParams.getIds()==null || deleteParams.getIds().length==0){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage(""));
        }
        elementSelectService.deletes(deleteParams.getIds());
        Integer id = elementSelectService.getElementSelect(deleteParams.getIds()[0]).getId();
        ItemParams<Integer> itemParams = new ItemParams<>();
        itemParams.setQueryParams(deleteParams.getQueryParams());
        itemParams.setItemPK(elementSelectService.getElementSelect(deleteParams.getIds()[0]).getElementId());
        return list(httpSession,itemParams);
    }
    @ResponseBody
    @RequiresPermissions(value = "view")
    @RequestMapping(value = "/getDataType",method = RequestMethod.GET)
    public JsonResult<String> getDataType(){
        JsonArray jsonArray = new JsonArray();
        JsonObject listObject = new JsonObject();
        for (JavaDataType javaDataType:JavaDataType.values()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key",javaDataType.getKey());
            jsonObject.addProperty("desc",javaDataType.getDesc());
            jsonArray.add(jsonObject);
        }
        listObject.add("javaDataType",jsonArray);
        return JsonResult.success(listObject.toString());
    }
}

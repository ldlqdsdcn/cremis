package com.dsdl.eidea.base.web.controller;

import com.dsdl.eidea.base.entity.po.ElementPo;
import com.dsdl.eidea.base.service.ElementService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.def.FieldShowType;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
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
@RequestMapping(value = "/base/element")
public class ElementController {
    private static final String URL = "core_element";
    @Autowired
    private ElementService elementService;

    @RequestMapping(value = "/showList", method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/base/element/element");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI, URL);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiresPermissions(value = "view")
    @ResponseBody
    public JsonResult<PaginationResult<ElementPo>> list(HttpSession session, @RequestBody QueryParams queryParams) {
        Search search = SearchHelper.getSearchParam(URL, session);
        PaginationResult<ElementPo> paginationResult = elementService.getElementListByPaging(search, queryParams);
        return JsonResult.success(paginationResult);
    }

    @RequiresPermissions(value = "add")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<ElementPo> create() {
        ElementPo elementPo = new ElementPo();
        return JsonResult.success(elementPo);
    }

    @RequiresPermissions(value = "view")
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public JsonResult<ElementPo> get(Integer id) {
        return JsonResult.success(elementService.getElement(id));
    }

    @RequestMapping(value = "/saveForCreate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<ElementPo> saveForCreate(@Validated @RequestBody ElementPo elementPo) {
        elementService.saveElement(elementPo);
        return JsonResult.success(elementPo);
    }

    @RequestMapping(value = "/saveForUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "update")
    public JsonResult<ElementPo> saveForUpate(@Validated @RequestBody ElementPo elementPo) {
        elementService.saveElement(elementPo);
        return JsonResult.success(elementPo);
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<ElementPo>> deletes(@RequestBody DeleteParams<Integer> deleteParams, HttpSession session) {
        UserResource userResource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (deleteParams.getIds() == null && deleteParams.getIds().length > 0) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), userResource.getMessage(""));
        }
        elementService.deletes(deleteParams.getIds());
        return list(session, deleteParams.getQueryParams());
    }
    @RequiresPermissions(value = "view")
    @ResponseBody
    @RequestMapping(value = "/showType",method = RequestMethod.GET)
    public JsonResult<String> showType(){
        JsonObject listObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(FieldShowType fieldShowType:FieldShowType.values()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("value",fieldShowType.getKey());
            jsonObject.addProperty("desc",fieldShowType.getDesc());
            jsonArray.add(jsonObject);
        }
        listObject.add("showTypeList",jsonArray);
        return JsonResult.success(listObject.toString());
    }

}

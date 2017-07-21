package cn.cityre.edi.mis.base.web.controller;

import cn.cityre.edi.mis.base.entity.po.MisApiKeyPo;
import cn.cityre.edi.mis.base.service.ApiKeyService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
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
 * Created by cityre on 2017/7/10.
 */
@Controller
@RequestMapping("/base/apikey")
public class ApiKeyController {
    private final static  String URL = "apikey";
    @Autowired
    private ApiKeyService apiKeyService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/base/apikey/apikey");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }
    @RequiresPermissions(value = "view")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisApiKeyPo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        Search search = SearchHelper.getSearchParam(URL,httpSession);
        PaginationResult<MisApiKeyPo> paginationResult = apiKeyService.getApiKeyList(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<MisApiKeyPo> get(HttpSession httpSession, String id){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        MisApiKeyPo misApiKeyPo = apiKeyService.getApiKey(id);
        return  JsonResult.success(misApiKeyPo);
    }
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @RequiresPermissions("add")
    public JsonResult<MisApiKeyPo> create(){
        MisApiKeyPo misApiKeyPo = new MisApiKeyPo();
        return  JsonResult.success(misApiKeyPo);
    }
    @ResponseBody
    @RequiresPermissions("add")
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    public JsonResult<MisApiKeyPo> saveForCreated(HttpSession httpSession, @Validated @RequestBody MisApiKeyPo misApiKeyPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        apiKeyService.createApiKey(misApiKeyPo);
        return JsonResult.success(misApiKeyPo);
    }
    @ResponseBody
    @RequiresPermissions("update")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<MisApiKeyPo> saveForUpdated(HttpSession httpSession , @Validated @RequestBody MisApiKeyPo misApiKeyPo){
        UserResource userResource =(UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        apiKeyService.updateApikey(misApiKeyPo);
        return JsonResult.success(misApiKeyPo);
    }
    @ResponseBody
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<MisApiKeyPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        apiKeyService.deleteById(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
    @ResponseBody
    @RequestMapping(value = "/logicDelete",method = RequestMethod.POST)
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<MisApiKeyPo>> logicDelete(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        apiKeyService.logicDeleteApiKey(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
}

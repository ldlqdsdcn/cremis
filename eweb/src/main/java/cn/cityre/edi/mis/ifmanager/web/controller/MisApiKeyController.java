package cn.cityre.edi.mis.ifmanager.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.ifmanager.entity.MisApiKeyPo;
import cn.cityre.mis.ifmanager.service.MisApiKeyService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by cityre on 2017/7/10.
 */
@Controller
@RequestMapping("/mis/ifmanager/apikey")
public class MisApiKeyController {
    private final static  String URL = "apikey";
    @Autowired
    private MisApiKeyService misApiKeyService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/mis/ifmanager/apikey/apikey");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }
    @RequiresPermissions(value = "view")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisApiKeyPo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        List<SearchField> search = SearchFieldHelper.getSearchField(URL,httpSession);
        PaginationResult<MisApiKeyPo> paginationResult = misApiKeyService.getApiKeyListByMybatis(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<MisApiKeyPo> get(HttpSession httpSession, String id){
        UserResource userResource = (UserResource) httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        MisApiKeyPo misApiKeyPo = misApiKeyService.getApiKey(id);
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
        misApiKeyService.createApiKey(misApiKeyPo);
        return JsonResult.success(misApiKeyPo);
    }
    @ResponseBody
    @RequiresPermissions("update")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<MisApiKeyPo> saveForUpdated(HttpSession httpSession , @Validated @RequestBody MisApiKeyPo misApiKeyPo){
        UserResource userResource =(UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misApiKeyService.updateApikey(misApiKeyPo);
        return JsonResult.success(misApiKeyPo);
    }
    @ResponseBody
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<MisApiKeyPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misApiKeyService.deleteById(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
    @ResponseBody
    @RequestMapping(value = "/logicDelete",method = RequestMethod.POST)
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<MisApiKeyPo>> logicDelete(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misApiKeyService.logicDeleteApiKey(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
}

package cn.cityre.edi.mis.ifmanager.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.ifmanager.entity.MisUserPhonePo;
import cn.cityre.mis.ifmanager.service.MisPhoneService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.util.SearchHelper;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import com.googlecode.genericdao.search.Search;
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
 * Created by cityre on 2017/7/12.
 */
@Controller(value = "misUserPhoneController")
@RequestMapping("/mis/ifmanager/userphone")
public class MisUserPhoneController {
    private final static String URL="user_phone";
    @Autowired
    private MisPhoneService misPhoneService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions("view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/mis/ifmanager/userphone/userphone");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        return modelAndView;
    }
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<PaginationResult<MisUserPhonePo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        List<SearchField> search = SearchFieldHelper.getSearchField(URL,httpSession);
        PaginationResult<MisUserPhonePo> paginationResult = misPhoneService.getUserPhoneListByMyBatis(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @ResponseBody
    @RequiresPermissions("view")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JsonResult<MisUserPhonePo> get(Integer id){
        MisUserPhonePo misUserPhonePo = misPhoneService.getExistPhoneById(id);
        return JsonResult.success(misUserPhonePo);
    }
    @ResponseBody
    @RequestMapping(value = "create",method = RequestMethod.GET)
    @RequiresPermissions("add")
    public JsonResult<MisUserPhonePo> create(){
        MisUserPhonePo misUserPhonePo = new MisUserPhonePo();
        return  JsonResult.success(misUserPhonePo);
    }
    @ResponseBody
    @RequestMapping(value = "saveForCreated",method = RequestMethod.POST)
    @RequiresPermissions("add")
    public JsonResult<MisUserPhonePo> saveForCreated(HttpSession httpSession , @Validated@RequestBody MisUserPhonePo misUserPhonePo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        if (misPhoneService.getExistPrimaryPhoneByUid(misUserPhonePo)){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage("error.user.phone.isPrimary.exist"));
        }
        misPhoneService.createPhone(misUserPhonePo);
        return JsonResult.success(misUserPhonePo);
    }
    @ResponseBody
    @RequestMapping(value = "saveForUpdated",method = RequestMethod.POST)
    @RequiresPermissions("add")
    public JsonResult<MisUserPhonePo> saveForUpdated(HttpSession httpSession , @Validated@RequestBody MisUserPhonePo misUserPhonePo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        if (misPhoneService.getExistPrimaryPhoneByUid(misUserPhonePo)){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage("error.user.phone.isPrimary.exist"));
        }
        misPhoneService.updateById(misUserPhonePo);
        return JsonResult.success(misUserPhonePo);
    }
    @ResponseBody
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    @RequiresPermissions("delete")
    public JsonResult<PaginationResult<MisUserPhonePo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misPhoneService.deleteById(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
}

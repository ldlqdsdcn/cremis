package cn.cityre.edi.mis.ifmanager.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.ifmanager.entity.MisUserEmailPo;
import cn.cityre.mis.ifmanager.service.MisEmailService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.DeleteParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
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
@Controller(value = "misUserEmailController")
@RequestMapping(value = "/mis/ifmanager/useremail")
public class MisUserEmailController {
    private final static String URL="user_email";
    @Autowired
    private MisEmailService misEmailService;

    @RequestMapping(value = "/showList",method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/mis/ifmanager/useremail/useremail");
        modelAndView.addObject(WebConst.PAGE_URI,URL);
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        return modelAndView;
    }
    @RequiresPermissions("view")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisUserEmailPo>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        List<SearchField> search = SearchFieldHelper.getSearchField(URL,httpSession);
        PaginationResult<MisUserEmailPo> paginationResult = misEmailService.getUserEmailListByMybatis(search,queryParams);
        return JsonResult.success(paginationResult);
    }
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @RequiresPermissions("view")
    @ResponseBody
    public JsonResult<MisUserEmailPo> get(Integer id){
        MisUserEmailPo misUserEmailPo=misEmailService.getExistUserEmailById(id);
        return JsonResult.success(misUserEmailPo);
    }
    @ResponseBody
    @RequiresPermissions("add")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public JsonResult<MisUserEmailPo> create(){
        MisUserEmailPo misUserEmailPo = new MisUserEmailPo();
        misUserEmailPo.setIsPrimary((byte) 0);
        misUserEmailPo.setIsVerified((byte) 0);
        return JsonResult.success(misUserEmailPo);
    }
    @ResponseBody
    @RequiresPermissions("add")
    @RequestMapping(value = "/saveForCreated",method = RequestMethod.POST)
    public JsonResult<MisUserEmailPo> saveForCreated(HttpSession httpSession, @Validated @RequestBody MisUserEmailPo misUserEmailPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misEmailService.createEmail(misUserEmailPo);
        return get(misUserEmailPo.getId());
    }
    @ResponseBody
    @RequiresPermissions("update")
    @RequestMapping(value = "/saveForUpdated",method = RequestMethod.POST)
    public JsonResult<MisUserEmailPo> saveForUpdated(HttpSession httpSession, @Validated @RequestBody MisUserEmailPo misUserEmailPo){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misEmailService.updateEmailById(misUserEmailPo);
        return get(misUserEmailPo.getId());
    }
    @ResponseBody
    @RequiresPermissions("delete")
    @RequestMapping(value = "/deletes",method = RequestMethod.POST)
    public JsonResult<PaginationResult<MisUserEmailPo>> deletes(HttpSession httpSession, @RequestBody DeleteParams<Integer> deleteParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        misEmailService.deleteEmailById(deleteParams.getIds());
        return list(httpSession,deleteParams.getQueryParams());
    }
}

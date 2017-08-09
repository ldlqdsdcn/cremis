package cn.cityre.edi.mis.datavip.web.controller;

import cn.cityre.edi.mis.mis.web.util.SearchFieldHelper;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.entity.UserList;
import cn.cityre.mis.datavip.service.UserListService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.core.web.result.def.ErrorCodes;
import com.dsdl.eidea.core.web.vo.PagingSettingResult;
import javafx.scene.control.Pagination;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by cityre on 2017/8/9.
 */
@Controller(value = "userListController")
@RequestMapping(value = "/mis/datavip/new")
public class UserListController {
    private static final String URL = "mis_userlist";

    @Autowired
    private UserListService userListService;

    @RequestMapping(value = "/showList")
    @RequiresPermissions(value = "view")
    @ResponseBody
    public ModelAndView showList(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("/mis/datavip/new/vipinfo");
        modelAndView.addObject(WebConst.PAGE_URI, URL);
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiresPermissions("view")
    public JsonResult<PaginationResult<UserList>> list(HttpSession httpSession, @RequestBody QueryParams queryParams){
        UserResource userResource = (UserResource)httpSession.getAttribute(WebConst.SESSION_RESOURCE);
        List<SearchField> searchFields = SearchFieldHelper.getSearchField(URL,httpSession);
        if (searchFields==null||searchFields.size()==0){
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(),userResource.getMessage(""));
        }
        PaginationResult<UserList> paginationResult = userListService.getExistUserInfoList(searchFields,queryParams);
        return JsonResult.success(paginationResult);
    }

}

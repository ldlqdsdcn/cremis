package com.dsdl.eidea.base.web.controller;

import cn.cityre.edi.mis.sys.entity.bo.LetterBo;
import com.dsdl.eidea.base.entity.bo.ClientBo;
import com.dsdl.eidea.base.entity.bo.OrgBo;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.base.service.ClientService;
import com.dsdl.eidea.base.service.OrgService;
import com.dsdl.eidea.base.service.UserService;
import com.dsdl.eidea.base.web.vo.UserAccessCitiesVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by Bobo on 2016/12/17 13:50.
 */
@Controller
@RequestMapping("/base/user")
public class UserController {
    private static final String URI = "sys_user";
    @Autowired
    private UserService userService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrgService orgService;

    /**
     * getUserToJsp:跳转user列表页
     *
     * @return
     */
    @RequestMapping(value = "/getUserToJsp", method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView getUserToJsp() {
        ModelAndView modelAndView = new ModelAndView("/base/user/user");
        modelAndView.addObject(WebConst.PAGE_URI, URI);
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        return modelAndView;
    }

    /**
     * getUserList:用户列表
     *
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<PaginationResult<UserBo>> getUserList(HttpServletRequest request, @RequestBody QueryParams queryParams) {
        Search search = SearchHelper.getSearchParam(URI, request.getSession());
//        search.addFilterIn("orgId",securityHelper.getAccessOrgList(request));
        PaginationResult<UserBo> userList = userService.getUserList(search, queryParams);
        return JsonResult.success(userList);
    }


    /**
     * deleteUserList:用户批量删除
     *
     * @param deleteParams
     * @return
     */
    @RequestMapping(value = "/deleteUserList", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<UserBo>> deleteUserList(@RequestBody DeleteParams<Integer> deleteParams, HttpServletRequest request, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        for (Integer i : deleteParams.getIds()) {
            UserBo userBo = userService.getUser(i);
            if (userBo.getInit().equals("Y")) {
                return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("useraccount.not.delete"));
            }
        }
        userService.deleteUserList(deleteParams.getIds());
        return getUserList(request, deleteParams.getQueryParams());
    }

    /**
     * save用户保存
     *
     * @param userBo
     * @return
     */
    @RequestMapping(value = "/saveUserForCreated", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<UserBo> saveUserForCreated(@Validated @RequestBody UserBo userBo, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (userService.findExistByUsername(userBo.getUsername())) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("common.connection.point"));
        }
        userService.saveUser(userBo);
        return getUser(userBo.getId(), session);
    }

    @RequestMapping(value = "/saveUserForUpdated", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "update")
    public JsonResult<UserBo> saveUserForUpdated(@Validated @RequestBody UserBo userBo, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (userBo.getId() == null) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("common.primary_key.isempty"));
        }
        UserBo userBoBase = userService.getUser(userBo.getId());
        boolean userHasExist = userService.getExistUserName(userBo);
        if (userBoBase.getUsername().equals(userBo.getUsername())) {
            userService.saveUser(userBo);
            return getUser(userBo.getId(), session);
        } else if (userHasExist) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("common.connection.point"));
        } else {
            userService.saveUser(userBo);
            return getUser(userBo.getId(), session);
        }
    }

    /**
     * get用户查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<UserBo> getUser(Integer id, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        UserBo userBo = null;
        if (id == null) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("client.msg.primary_key_validation"));
        } else {
            userBo = userService.getUser(id);
        }
        return JsonResult.success(userBo);
    }

    /**
     * get用户查询
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<UserBo> create() {
        return JsonResult.success(new UserBo());
    }

    /**
     * getExistUserName:检验用户名是否存在
     *
     * @param userBo
     * @return
     */
    @RequestMapping(value = "/getExistUserName", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<Boolean> getExistUserName(@RequestBody UserBo userBo, HttpSession session) {
        boolean flag = false;
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (userBo.getUsername() == null || userBo.getUsername().equals("")) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("logon.name.isnot.empty"));
        } else {
            flag = userService.getExistUserName(userBo);
        }
        return JsonResult.success(flag);
    }
    @RequestMapping(value = "/saveUserAccessCitiesPrivileges", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "update")
    public JsonResult<UserAccessCitiesVo> saveUserAccessCitiesPrivileges(@RequestBody UserAccessCitiesVo userAccessCitiesVo) {
        userService.saveUserAccessCitiesPrivileges(userAccessCitiesVo.getUserId(), userAccessCitiesVo.getLetterBoList());
        return getUserAccessCities(userAccessCitiesVo.getUserId());
    }

    @RequestMapping(value = "/getUserAccessCities/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<UserAccessCitiesVo> getUserAccessCities(@PathVariable("userId") Integer userId) {
        UserBo userBo = userService.getUser(userId);
        List<LetterBo> letterBoList = userService.getProvinceAccessList(userId);
        UserAccessCitiesVo userAccessCitiesVo = new UserAccessCitiesVo();
        userAccessCitiesVo.setLetterBoList(letterBoList);
        userAccessCitiesVo.setUserId(userBo.getId());
        userAccessCitiesVo.setUsername(userBo.getUsername());
        userAccessCitiesVo.setName(userBo.getName());
        ClientBo client = clientService.getClientBo(userBo.getClientId());
        OrgBo orgBo = orgService.getOrgBo(userBo.getOrgId());
        userAccessCitiesVo.setClientName(client.getName());
        userAccessCitiesVo.setOrgName(orgBo.getName());
        return JsonResult.success(userAccessCitiesVo);
    }
}

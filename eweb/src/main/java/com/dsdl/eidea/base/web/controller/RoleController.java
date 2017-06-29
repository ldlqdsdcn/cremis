package com.dsdl.eidea.base.web.controller;

import cn.cityre.edi.mis.sys.entity.bo.LetterBo;
import com.dsdl.eidea.base.entity.bo.*;
import com.dsdl.eidea.base.service.RoleService;
import com.dsdl.eidea.base.web.vo.RoleAccessCitiesVo;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/base/role")
public class RoleController {
    private static final String URI = "sys_role";
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/showList", method = RequestMethod.GET)
    @RequiresPermissions(value = "view")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/base/role/role");
        modelAndView.addObject(WebConst.PAGING_SETTINGS, PagingSettingResult.getDbPaging());
        modelAndView.addObject(WebConst.PAGE_URI, URI);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<PaginationResult<RoleBo>> list(HttpSession session, @RequestBody QueryParams queryParams) {
        Search search = SearchHelper.getSearchParam(URI, session);
        PaginationResult<RoleBo> roleBoList = roleService.getRoleList(search,queryParams);
        return JsonResult.success(roleBoList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<RoleBo> create() {
        RoleBo roleBo = roleService.getInitRoleBo(null);
        return JsonResult.success(roleBo);
    }

    @RequestMapping(value = "/saveForCreated", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "add")
    public JsonResult<RoleBo> saveForCreated(@RequestBody RoleBo roleBo, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (roleService.findExistRole(roleBo.getNo())) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("role.number.equal"));
        }
        roleService.save(roleBo);
        return get(roleBo.getId(), session);
    }

    @RequestMapping(value = "/saveForUpdated", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "update")
    public JsonResult<RoleBo> saveForUpdated(@RequestBody RoleBo roleBo, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        RoleBo roleBoOld=roleService.getRoleBo(roleBo.getId());
        if (roleBoOld.getNo().equals(roleBo.getNo())){
            roleService.save(roleBo);
            return get(roleBo.getId(), session);
        }else if (roleService.findExistRole(roleBo.getNo())){
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("role.number.equal"));
        }else{
            roleService.save(roleBo);
            return get(roleBo.getId(), session);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<RoleBo> get(Integer id, HttpSession session) {
        RoleBo roleBo = null;
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (id == null) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("client.msg.primary_key_validation"));
        } else {
            roleBo = roleService.getRoleBo(id);
        }
        return JsonResult.success(roleBo);
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "delete")
    public JsonResult<PaginationResult<RoleBo>> deletes(@RequestBody DeleteParams<Integer> deleteParams, HttpSession session) {
        UserResource resource = (UserResource) session.getAttribute(WebConst.SESSION_RESOURCE);
        if (deleteParams.getIds() == null || deleteParams.getIds().length == 0) {
            return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("pagemenu.choose.information"));
        }
        for (Integer id : deleteParams.getIds()) {
            boolean isExist = roleService.getHasUsers(id);
            if (isExist) {
                return JsonResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(), resource.getMessage("role.error.has_users"));
            }
        }
        roleService.deletes(deleteParams.getIds());
        return list(session,deleteParams.getQueryParams());
    }

    @RequestMapping(value = "/saveRoleAccessCitiesPrivileges", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions(value = "update")
    public JsonResult<RoleAccessCitiesVo> saveRoleAccessCitiesPrivileges(@RequestBody RoleAccessCitiesVo userAccessCitiesVo) {
        roleService.saveRoleAccessCitiesPrivileges(userAccessCitiesVo.getRoleId(), userAccessCitiesVo.getLetterBoList());
        return getRoleAccessCities(userAccessCitiesVo.getRoleId());
    }

    @RequestMapping(value = "/getRoleAccessCities/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = "view")
    public JsonResult<RoleAccessCitiesVo> getRoleAccessCities(@PathVariable("roleId") Integer roleId) {
        RoleBo roleBo = roleService.getRoleBo(roleId);
        List<LetterBo> letterBoList = roleService.getProvinceAccessList(roleId);
        RoleAccessCitiesVo roleAccessCitiesVo = new RoleAccessCitiesVo();
        roleAccessCitiesVo.setLetterBoList(letterBoList);
        roleAccessCitiesVo.setRoleId(roleId);
        roleAccessCitiesVo.setName(roleBo.getName());
        return JsonResult.success(roleAccessCitiesVo);
    }
}

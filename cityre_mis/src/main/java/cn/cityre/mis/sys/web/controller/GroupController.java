package cn.cityre.mis.sys.web.controller;

import cn.cityre.mis.core.web.annon.MisValid;
import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.bo.GroupCityBo;
import cn.cityre.mis.sys.entity.query.GroupQuery;
import cn.cityre.mis.sys.entity.union.GroupRepositoryUnion;
import cn.cityre.mis.sys.entity.vo.GroupCitiesVo;
import cn.cityre.mis.sys.entity.vo.GroupVo;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.model.Group;
import cn.cityre.mis.sys.service.GroupService;
import cn.cityre.mis.util.WebUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/28 12:24.
 * 用户组管理
 */
@RequestMapping("/sys/group")
@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;
    @RequestMapping("/")
    @RequiresPermissions("group:view")
    public ModelAndView showGroup() {
        return new ModelAndView("sys/group/group");
    }

    @RequiresPermissions("group:view")
    @RequestMapping("/showList")
    public ModelAndView showList() {
        return new ModelAndView("sys/group/list");
    }

    @RequiresPermissions("group:view")
    @RequestMapping("/list")
    public List<Group> list(GroupQuery groupQuery, HttpServletRequest request) {
        return groupService.getGroupList(groupQuery);
    }

    @RequiresPermissions("group:view")
    @RequestMapping("/showForm")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("sys/group/form");
        return modelAndView;
    }

    @RequiresPermissions("group:view")
    @RequestMapping("/get/{id}")
    public JsonResult<GroupVo> get(@PathVariable("id") Integer id) {
        if (id == -1) {
            id = null;
        }
        GroupVo groupVo = new GroupVo();
        List<GroupRepositoryUnion> repositoryUnionList = null;
        Group group = null;
        if (id == null || id == -1) {
            group = new Group();
        } else {
            group = groupService.getGroup(id);

        }
        repositoryUnionList = groupService.getGroupRepositoryList(id);
        groupVo.setGroup(group);
        groupVo.setGroupRepositoryUnionList(repositoryUnionList);
        return JsonResult.success(groupVo);
    }

    @RequiresPermissions("group:edit")
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult<GroupVo> save(@RequestBody @MisValid GroupVo groupVo, HttpServletRequest request) {
        /*
        String errorMsg = validatorHelper.validatorBackHtmlString(groupVo);
        if (errorMsg != null) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), errorMsg);
        }*/
        UserSession userSession = (UserSession) request.getSession().getAttribute(WebConstant.USER_IN_SESSION);
        groupVo.getGroup().setUpdated(new Date());
        groupVo.getGroup().setUpdatedby(userSession.getUnionUid());
        groupService.saveGroup(groupVo.getGroup(), groupVo.getGroupRepositoryUnionList());
        return JsonResult.success(null);

    }

    @RequiresPermissions("group:edit")
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult<Void> deleteGroups(@RequestBody Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "请选择用户组再删除");
        } else {
            groupService.deleteGroupByIds(Arrays.asList(ids));
        }
        return JsonResult.success(null);
    }

    @RequiresPermissions("group:view")
    @RequestMapping("/showCities/{groupId}")
    public ModelAndView showCities(@PathVariable("groupId") Integer groupId) {

        GroupCityBo groupCityBo = groupService.getGroupCityBo(groupId);
        ModelAndView modelAndView = new ModelAndView("sys/group/cities");
        modelAndView.addObject("groupCity", groupCityBo);
        return modelAndView;
    }

    @RequiresPermissions("group:edit")
    @RequestMapping(value = "/saveCities", method = RequestMethod.POST)
    public JsonResult<Void> saveCities(@RequestBody GroupCitiesVo groupCitiesVo, HttpServletRequest request) {
        UserSession userSession = WebUtil.getUserSession(request);
        groupService.saveGroupCities(groupCitiesVo.getGroupId(), groupCitiesVo.getCities(), userSession.getUnionUid());
        return JsonResult.success(null);
    }
}

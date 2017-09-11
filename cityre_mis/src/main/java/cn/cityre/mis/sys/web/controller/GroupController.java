package cn.cityre.mis.sys.web.controller;

import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.query.GroupQuery;
import cn.cityre.mis.sys.entity.union.GroupRepositoryUnion;
import cn.cityre.mis.sys.entity.vo.GroupVo;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.model.Group;
import cn.cityre.mis.sys.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public ModelAndView showGroup() {
        return new ModelAndView("sys/group/group");
    }

    @RequestMapping("/showList")
    public ModelAndView showList() {
        return new ModelAndView("sys/group/list");
    }

    @RequestMapping("/list")
    public List<Group> list(GroupQuery groupQuery, HttpServletRequest request) {
        return groupService.getGroupList(groupQuery);
    }

    @RequestMapping("/showForm")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("sys/group/form");
        return modelAndView;
    }

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

    @RequestMapping("/save")
    @ResponseBody
    public JsonResult<GroupVo> save(@Validated @RequestBody GroupVo groupVo, HttpServletRequest request,BindingResult bindingResult) {
        if (bindingResult.getFieldErrorCount()>0){
            String message="";
            for (int i=0;i<bindingResult.getFieldErrors().size();i++){
                message=message+" "+bindingResult.getFieldErrors().get(i).getDefaultMessage();
            }
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), message);
        }
        UserSession userSession = (UserSession) request.getSession().getAttribute(WebConstant.USER_IN_SESSION);
        groupVo.getGroup().setUpdated(new Date());
        groupVo.getGroup().setUpdatedby(userSession.getUnionUid());
        groupService.saveGroup(groupVo.getGroup(), groupVo.getGroupRepositoryUnionList());
        return JsonResult.success(null);

    }
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
}
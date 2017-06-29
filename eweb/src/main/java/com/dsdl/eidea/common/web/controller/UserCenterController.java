package com.dsdl.eidea.common.web.controller;

import cn.cityre.edi.mis.base.entity.po.CityPo;
import cn.cityre.edi.mis.base.entity.po.ProvincePo;
import cn.cityre.edi.mis.base.service.CityService;
import com.dsdl.eidea.base.entity.bo.ChangePasswordBo;
import com.dsdl.eidea.base.entity.bo.UserBo;
import com.dsdl.eidea.base.entity.bo.UserContent;
import com.dsdl.eidea.base.service.UserService;
import com.dsdl.eidea.base.web.vo.UserResource;
import com.dsdl.eidea.common.web.vo.UserProfileVo;
import com.dsdl.eidea.core.web.def.WebConst;
import com.dsdl.eidea.core.web.result.JsonResult;
import com.dsdl.eidea.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 刘大磊 on 2016/12/21 9:23.
 */
@Controller
@RequestMapping("/common/userCenter")
public class UserCenterController {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    /**
     * changePassword:用户修改密码
     *
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<String> changePassword(HttpServletRequest request, @Validated @RequestBody ChangePasswordBo userBo) {
        UserResource resource = (UserResource) request.getSession().getAttribute(WebConst.SESSION_RESOURCE);
        UserBo user = (UserBo) request.getSession().getAttribute("loginUser");
        if (user == null) {
            return JsonResult.fail(0, resource.getMessage("change.password.msg.login.timeout"));
        }
        if (!userBo.getOldPassword().equals(user.getPassword())) {
            return JsonResult.fail(0, resource.getMessage("change.password.msg.old.password.error"));
        }
        UserBo userParam = userService.getUser(user.getId());
        userParam.setPassword(userBo.getPassword());
        userService.saveUser(userParam);
        return JsonResult.success(resource.getMessage("change.password.msg.change.success"));
    }

    /**
     * 查看个人信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<UserProfileVo> getProfile(HttpServletRequest request) {
        UserProfileVo userProfileVo = new UserProfileVo();
        String token = request.getHeader(WebConst.HEADER_TOKEN);
        UserContent userContent = null;
        UserBo userBo = null;
        if (StringUtil.isEmpty(token)) {
            userContent = (UserContent) request.getSession().getAttribute(WebConst.SESSION_USERCONTENT);
            userBo = (UserBo) request.getSession().getAttribute(WebConst.SESSION_LOGINUSER);
        } else {
            userContent = userService.getUserContent(token);
            userBo = userService.getUser(userContent.getUserSessionBo().getUserId());
        }
        userProfileVo.setUserContent(userContent);
        userProfileVo.setUser(userBo);
        return JsonResult.success(userProfileVo);
    }

    /**
     * 修改个人信息
     *
     * @param userBo
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<UserProfileVo> updateProfile(@RequestBody UserBo userBo, HttpServletRequest request) {
        userService.saveUserForProfile(userBo);
        return getProfile(request);
    }
    @RequestMapping(value = "/selectCity/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectCity(@PathVariable("cityId") Integer cityId, HttpSession session)
    {
        CityPo cityPo=cityService.getCity(cityId);
        session.setAttribute(WebConst.SESSION_CURRENT_CITY,cityPo);
        return JsonResult.success("选择城市成功！");
    }
    @RequestMapping(value = "/getProvinceList", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<ProvincePo>> getProvinceList()
    {
        return null;
    }

    public JsonResult<List<CityPo>> getCityList()
    {
        return null;
    }
}

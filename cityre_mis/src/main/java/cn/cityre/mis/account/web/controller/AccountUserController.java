package cn.cityre.mis.account.web.controller;

import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.entity.union.UserGroupUnion;
import cn.cityre.mis.account.entity.union.UserRepositoryUnion;
import cn.cityre.mis.account.entity.vo.AccountUserCitiesVo;
import cn.cityre.mis.account.entity.vo.AccountUserVo;
import cn.cityre.mis.account.model.AccountUser;
import cn.cityre.mis.account.service.AccountUserService;
import cn.cityre.mis.core.entity.result.PagingListResult;
import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.sys.entity.bo.UserCityBo;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.service.UserService;
import cn.cityre.mis.util.WebUtil;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/4 10:34.
 * 用户账户管理
 */
@Controller
@RestController
@RequestMapping("/account/user")
public class AccountUserController {
    @Autowired
    private AccountUserService accountUserService;
    @Autowired
    private UserService userService;

    @RequestMapping("/showAccount")
    @RequiresPermissions("account:view")
    public ModelAndView showGroup() {
        return new ModelAndView("account/user");
    }

    @RequestMapping("/showList")
    @RequiresPermissions("account:view")
    public ModelAndView showList() {
        return new ModelAndView("account/list.tpl");
    }

    @RequestMapping("/showForm")
    @RequiresPermissions("account:view")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("account/form.tpl");
        return modelAndView;
    }

    @RequestMapping("/get/{id}")
    @RequiresPermissions("account:view")
    public JsonResult<AccountUserVo> get(@PathVariable("id") Integer id) {
        if (id == -1) {
            id = null;
        }
        AccountUserVo accountUserVo = new AccountUserVo();
        List<UserRepositoryUnion> repositoryUnionList = null;
        AccountUser accountUser = null;
        if (id == null) {
            accountUser = new AccountUser();
        } else {
            accountUser = accountUserService.getAccountUser(id);
        }
        repositoryUnionList = accountUserService.getAccountPrivileges(accountUser.getUnionuid());
        accountUserVo.setAccountUser(accountUser);
        accountUserVo.setUserRepositoryUnionList(repositoryUnionList);
        List<UserGroupUnion> userGroupList = accountUserService.getUserGroupUnionList(accountUser.getUnionuid());
        accountUserVo.setUserGroupUnionList(userGroupList);
        return JsonResult.success(accountUserVo);
    }

    @RequestMapping("/list")
    @RequiresPermissions("account:view")
    public PagingListResult<AccountUser> list(AccountUserQuery accountUserQuery) {
        PagingListResult<AccountUser> pagingListResult = new PagingListResult<>();
        Page<AccountUser> pageMyBatis = accountUserService.getAccountUserList(accountUserQuery);
        pagingListResult.setRows(pageMyBatis);
        pagingListResult.setTotal(pageMyBatis.getTotal());
        return pagingListResult;
    }

    @RequestMapping("/showCities/{unionUid}")
    @RequiresPermissions("account:view")
    public ModelAndView showCities(@PathVariable("unionUid") Integer id) {
        UserCityBo userCityBo = userService.getUserCityBo(id);
        ModelAndView modelAndView = new ModelAndView("account/cities");
        modelAndView.addObject("userCity", userCityBo);
        return modelAndView;
    }
    @RequestMapping(value = "/saveCities",method = RequestMethod.POST)
    @RequiresPermissions("account:edit")
    public JsonResult<Void> saveCities(@RequestBody AccountUserCitiesVo accountUserCitiesVo, HttpServletRequest request) {
        UserSession userSession = WebUtil.getUserSession(request);
        userService.saveUserCities(accountUserCitiesVo.getUnionUid(), accountUserCitiesVo.getCities(), userSession.getUnionUid());
        return JsonResult.success(null);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("account:edit")
    public JsonResult<AccountUserVo> saveAccountUser(@RequestBody AccountUserVo accountUserVo, HttpServletRequest request) {
        UserSession userSession = (UserSession) request.getSession().getAttribute(WebConstant.USER_IN_SESSION);
        //TODO添加验证
        accountUserService.saveAccount(accountUserVo.getAccountUser(), accountUserVo.getUserRepositoryUnionList(), accountUserVo.getUserGroupUnionList(), userSession.getUnionUid());
        return JsonResult.success(null);
    }
}

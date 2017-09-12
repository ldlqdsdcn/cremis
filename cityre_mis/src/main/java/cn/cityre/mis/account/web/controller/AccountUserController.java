package cn.cityre.mis.account.web.controller;

import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.entity.union.UserGroupUnion;
import cn.cityre.mis.account.entity.union.UserRepositoryUnion;
import cn.cityre.mis.account.entity.vo.AccountUserVo;
import cn.cityre.mis.account.model.AccountUser;
import cn.cityre.mis.account.service.AccountUserService;
import cn.cityre.mis.core.entity.result.PagingListResult;
import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.sys.entity.bo.UserCityBo;
import cn.cityre.mis.sys.entity.union.GroupRepositoryUnion;
import cn.cityre.mis.sys.entity.vo.GroupVo;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.model.Group;
import cn.cityre.mis.sys.service.UserService;
import org.mybatis.pagination.dto.PageMyBatis;
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
    public ModelAndView showGroup() {
        return new ModelAndView("account/user");
    }

    @RequestMapping("/showList")
    public ModelAndView showList() {
        return new ModelAndView("account/list.tpl");
    }

    @RequestMapping("/showForm")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("account/form.tpl");
        return modelAndView;
    }

    @RequestMapping("/get/{id}")
    public JsonResult<AccountUserVo> get(@PathVariable("id") Integer id) {
        if (id == -1) {
            id = null;
        }
        AccountUserVo accountUserVo = new AccountUserVo();
        List<UserRepositoryUnion> repositoryUnionList = null;
        AccountUser accountUser = null;
        if (id == null || id == -1) {
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
    public PagingListResult<AccountUser> list(AccountUserQuery accountUserQuery) {
        PagingListResult<AccountUser> pagingListResult = new PagingListResult<>();
        PageMyBatis<AccountUser> pageMyBatis = accountUserService.getAccountUserList(accountUserQuery);
        pagingListResult.setRows(pageMyBatis);
        pagingListResult.setTotal(pageMyBatis.getTotal());
        return pagingListResult;
    }

    @RequestMapping("/showCities/{unionUid}")
    public ModelAndView showCities(@PathVariable("unionUid") String unionUid) {
        UserCityBo userCityBo = userService.getUserCityBo(unionUid);
        ModelAndView modelAndView = new ModelAndView("account/citys");
        modelAndView.addObject("userCity", userCityBo);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<AccountUserVo> saveAccountUser(@RequestBody AccountUserVo accountUserVo, HttpServletRequest request) {
        UserSession userSession = (UserSession) request.getSession().getAttribute(WebConstant.USER_IN_SESSION);
        //TODO添加验证
        accountUserService.saveAccount(accountUserVo.getAccountUser(), accountUserVo.getUserRepositoryUnionList(), accountUserVo.getUserGroupUnionList(), userSession.getUnionUid());
        return JsonResult.success(null);
    }
}

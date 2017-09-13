package cn.cityre.mis.sys.web.controller;

import cn.cityre.mis.common.web.vo.MenuVo;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.query.MenuQuery;
import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.entity.union.MenuUnion;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.model.Menu;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.service.MenuService;
import cn.cityre.mis.sys.service.RepositoryService;
import cn.cityre.mis.util.WebUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/24 17:29.
 * 菜单管理
 */
@Controller
@RequestMapping("/sys/menu")
@RequiresPermissions("sys_menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RepositoryService repositoryService;

    @RequiresPermissions("menu:view")
    @RequestMapping("/showMenu")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("sys/menu");
        return modelAndView;
    }

    @RequiresPermissions("menu:view")
    @RequestMapping("/list")
    @ResponseBody
    public List<MenuUnion> getMenus(MenuQuery menuQuery) {
        List<MenuUnion> menuList = menuService.selectList(menuQuery);
        return menuList;
    }

    @RequiresPermissions("menu:edit")
    @RequestMapping("/deletes")
    @ResponseBody
    public JsonResult<Void> deletes(@RequestBody Integer[] ids, HttpServletRequest request) {

        if (ids == null || ids.length == 0) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "请先选中再删除");
        }
        menuService.deleteMenuList(Arrays.asList(ids));
        return JsonResult.success(null);
    }

    @RequiresPermissions("menu:view")
    @RequestMapping("/repositories")
    @ResponseBody
    public List<Repository> repositories() {
        RepositoryQuery param = new RepositoryQuery();
        param.setIsactive("Y");
        return repositoryService.getList(param);
    }

    @RequiresPermissions("menu:view")
    @RequestMapping("/menuFolders")
    @ResponseBody
    public List<Menu> menuFolders() {
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setIsactive("Y");
        menuQuery.setType(0);
        return menuService.selectMenuList(menuQuery);
    }

    @RequiresPermissions("menu:edit")
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult<Menu> save(@RequestBody Menu menu, HttpServletRequest request) {
        UserSession userSession = WebUtil.getUserSession(request);
        menu.setUpdatedby(userSession.getUnionUid());
        menu.setUpdated(new Date());
        menuService.saveMenu(menu);
        return JsonResult.success(null);
    }
}

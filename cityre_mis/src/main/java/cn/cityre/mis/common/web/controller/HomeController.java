package cn.cityre.mis.common.web.controller;

import cn.cityre.mis.common.web.vo.MenuVo;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.model.Menu;
import cn.cityre.mis.sys.service.MenuService;
import cn.cityre.mis.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/22 11:25.
 * 首页
 */
@Controller
public class HomeController {
    @Autowired
    private MenuService menuService;
    @RequestMapping({"index", "/"})
    public ModelAndView showHome() {
        return new ModelAndView("home");
    }

    @RequestMapping("/kouhao")
    public String showKouhao() {
        return "common/kouhao";
    }

    @RequestMapping("error/noprivileges")
    public String noprivileges() {
        return "error/noprivileges";
    }

    @RequestMapping("/common/frameLogin")
    public String frameLogin() {
        return "common/frameLogin";
    }

    @RequestMapping("/desktop")
    public String desktop() {
        return "desktop";
    }

    @RequestMapping("/leftmenu")
    @ResponseBody
    public JsonResult<List<MenuVo>> getLeftMenuList(HttpSession session) {
        UserSession userSession=WebUtil.getUserSession(session);
        String unionUid=userSession.getUnionUid();
        List<Menu> menuList=menuService.getLeftmenuList(unionUid);


        List<MenuVo> menuVoList = new ArrayList<>();
        MenuVo menuVo = new MenuVo();
        menuVo.setId(0);
        menuVo.setpId(-1);
        menuVo.setMenuType(0);
        menuVo.setState("open");
        menuVo.setName("菜单");
        menuVo.setOpen(true);
        menuVo.setSort(1);
        menuVoList.add(menuVo);
        for(Menu menu:menuList)
        {
            menuVo = new MenuVo();
            menuVo.setId(menu.getId());
            Integer pid=menu.getParentMenuId();
            if(pid==null)
            {
                pid=0;
            }
            menuVo.setpId(pid);
            menuVo.setMenuType(menu.getType()& 0xFF);
            menuVo.setName(menu.getName());
            menuVo.setOpen(true);
            menuVo.setSort(menu.getSortNo());
            menuVo.setState("open");
            menuVo.setMenuView(menu.getUrl());
            menuVoList.add(menuVo);
        }

        return JsonResult.success(menuVoList);
    }
}

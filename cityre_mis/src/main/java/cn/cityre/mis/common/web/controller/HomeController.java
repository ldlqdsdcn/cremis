package cn.cityre.mis.common.web.controller;

import cn.cityre.mis.center.entity.vo.CityVo;
import cn.cityre.mis.center.entity.vo.ProvinceVo;
import cn.cityre.mis.center.model.City;
import cn.cityre.mis.center.model.Province;
import cn.cityre.mis.center.service.CityService;
import cn.cityre.mis.common.web.vo.MenuVo;
import cn.cityre.mis.core.web.def.WebConstant;
import cn.cityre.mis.core.web.result.JsonResult;
import cn.cityre.mis.core.web.result.def.ErrorCodes;
import cn.cityre.mis.sys.entity.vo.UserSession;
import cn.cityre.mis.sys.model.Menu;
import cn.cityre.mis.sys.service.MenuService;
import cn.cityre.mis.util.StringUtil;
import cn.cityre.mis.util.WebUtil;
import com.mongodb.util.JSON;
import org.apache.commons.collections.list.PredicatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private CityService cityService;

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
        UserSession userSession = WebUtil.getUserSession(session);
        String unionUid = userSession.getUnionUid();
        List<Menu> menuList = menuService.getLeftmenuList(unionUid);


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
        for (Menu menu : menuList) {
            menuVo = new MenuVo();
            menuVo.setId(menu.getId());
            Integer pid = menu.getParentMenuId();
            if (pid == null) {
                pid = 0;
            }
            menuVo.setpId(pid);
            menuVo.setMenuType(menu.getType() & 0xFF);
            menuVo.setName(menu.getName());
            menuVo.setOpen(true);
            menuVo.setSort(menu.getSortNo());
            menuVo.setState("open");
            menuVo.setMenuView(menu.getUrl());
            menuVoList.add(menuVo);
        }
        return JsonResult.success(menuVoList);
    }

    @RequestMapping("/city/{provinceCode}")
    @ResponseBody
    public JsonResult<List<CityVo>> getCityList(@PathVariable("provinceCode") String provinceCode) {
        if (StringUtil.isEmpty(provinceCode)) {
            return JsonResult.fail(ErrorCodes.VALIDATE_PARAM_ERROR.getCode(), "省份编码不允许为空");
        }
        List<City> cities = null;
        String unionUid = WebUtil.getUnionUid();
        if (WebConstant.ADMINISTRATOR.equals(unionUid)) {
            cities = cityService.getCityListByProvinceCode(provinceCode);
        } else {
            cities = cityService.getCityListByProvinceCodeAndUnionUid(provinceCode, unionUid);
        }
        List<CityVo> cityVoList = new ArrayList<>();
        for (City city : cities) {
            CityVo cityVo = new CityVo();
            cityVo.setCode(city.getCityCode());
            cityVo.setName(city.getCityName());
            cityVo.setProvinceCode(city.getProvinceCode());
            cityVoList.add(cityVo);
        }
        return JsonResult.success(cityVoList);
    }

    @RequestMapping("/provinces")
    @ResponseBody
    public JsonResult<List<ProvinceVo>> getProvinceList(HttpSession session) {
        List<Province> provinceList = null;
        String unionUid = WebUtil.getUnionUid(session);
        if (WebConstant.ADMINISTRATOR.equals(unionUid)) {
            provinceList = cityService.getAllProvinceList();
        } else {
            provinceList = cityService.getProvinceByUnionUid(unionUid);
        }
        List<ProvinceVo> provinceVoList = new ArrayList<>();
        for (Province province : provinceList) {
            ProvinceVo provinceVo = new ProvinceVo();
            provinceVo.setCode(province.getProvinceCode());
            provinceVo.setName(province.getProvinceName());
            provinceVoList.add(provinceVo);
        }
        return JsonResult.success(provinceVoList);
    }

    @RequestMapping("/selectCity/{cityCode}")
    @ResponseBody
    public JsonResult<City> selectCity(@PathVariable String cityCode, HttpSession session) {
        WebUtil.getUserSession(session).setCurrentCityCode(cityCode);
        City city = cityService.getCityByCode(cityCode);
        WebUtil.getUserSession(session).setCurrentProvinceCode(city.getProvinceCode());
        WebUtil.getUserSession(session).setCityName(city.getCityName());
        return JsonResult.success(city);
    }

    @RequestMapping("/userSession")
    @ResponseBody
    public JsonResult<UserSession> getUserSession(HttpSession session) {
        return JsonResult.success(WebUtil.getUserSession(session));
    }
}

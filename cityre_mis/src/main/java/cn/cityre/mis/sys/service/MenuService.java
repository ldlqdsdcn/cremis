package cn.cityre.mis.sys.service;

import cn.cityre.mis.sys.entity.query.MenuQuery;
import cn.cityre.mis.sys.entity.union.MenuUnion;
import cn.cityre.mis.sys.model.Menu;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/28 12:06.
 */
public interface MenuService {
    List<MenuUnion> selectList(MenuQuery menuQuery);

    void saveMenu(Menu menu);

    void deleteMenuList(List<Integer> menuList);

    List<Menu> selectMenuList(MenuQuery menuQuery);

    List<Menu> getLeftmenuList(String unionUid);
}

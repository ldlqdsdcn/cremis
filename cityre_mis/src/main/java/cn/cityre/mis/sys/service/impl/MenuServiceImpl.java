package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.sys.dao.MenuMapper;
import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.entity.query.MenuQuery;
import cn.cityre.mis.sys.entity.union.MenuUnion;
import cn.cityre.mis.sys.model.Menu;
import cn.cityre.mis.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 刘大磊 on 2017/8/28 12:06.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;

    @Override
    public List<MenuUnion> selectList(MenuQuery menuQuery) {
        return menuMapper.selectList(menuQuery);
    }

    public void saveMenu(Menu menu) {
        boolean isnew = false;
        if (menu.getId() == null) {
            menu.setCreated(menu.getUpdated());
            menu.setCreatedby(menu.getUpdatedby());
            isnew = true;
        }
        if (isnew) {
            menuMapper.insert(menu);
        } else {
            menuMapper.updateByPrimaryKeySelective(menu);
        }
    }

    public void deleteMenuList(List<Integer> menuList) {
        if (menuList != null) {
            for (Integer id : menuList) {
                menuMapper.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public List<Menu> selectMenuList(MenuQuery menuQuery) {
        return menuMapper.selectMenuList(menuQuery);
    }

    public List<Menu> getLeftmenuList(String unionUid) {
        if ("administrator".equals(unionUid)) {
            return menuMapper.selectMenuList(new MenuQuery());
        }
        Set<Integer> integerList = repositoryMapper.getUserRepositoryIds(unionUid);
        if (integerList.size() == 0) {
            return new ArrayList<>();
        }
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setRepInIds(integerList);
        List<Menu> menuList = menuMapper.selectMenuList(menuQuery);
        //如果菜单列表取出来，但父菜单没权限，则找到父菜单添加到列表中。
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            addParentMenu(menu, menuList);
        }
        return menuList;
    }

    @Override
    public List<Menu> getLeftmenuList(String unionUid, Integer menuId) {
        //FIXME 需要在性能上做优化
        List<Menu> menuList = getLeftmenuList(unionUid);
        List<Menu> levelMenuList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menuId == null) {
                if (menu.getParentMenuId() == null) {
                    levelMenuList.add(menu);
                }
            } else {
                if (menuId.equals(menu.getParentMenuId())) {
                    levelMenuList.add(menu);
                }
            }
        }
        return levelMenuList;
    }

    private void addParentMenu(Menu menu, List<Menu> menuList) {
        if (!containsParentMenu(menuList, menu.getParentMenuId())) {
            Menu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentMenuId());
            menuList.add(parentMenu);
            addParentMenu(parentMenu, menuList);
        }
    }

    private boolean containsParentMenu(List<Menu> menus, Integer parentMenuId) {
        if (parentMenuId == null) {
            return true;
        }
        for (Menu menu : menus) {
            if (menu.getId().equals(parentMenuId)) {
                return true;
            }
        }
        return false;
    }
}

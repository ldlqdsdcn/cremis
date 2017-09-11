package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.entity.query.MenuQuery;
import cn.cityre.mis.sys.entity.union.MenuUnion;
import cn.cityre.mis.sys.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenuUnion> selectList(MenuQuery menuQuery);

    List<Menu> selectMenuList(MenuQuery menuQuery);
}
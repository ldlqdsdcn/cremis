package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.entity.query.UserCityQuery;
import cn.cityre.mis.sys.model.UserCity;

import java.util.List;

public interface UserCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCity record);

    int insertSelective(UserCity record);

    UserCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCity record);

    int updateByPrimaryKey(UserCity record);

    List<UserCity> selectList(UserCityQuery userCityQuery);
}
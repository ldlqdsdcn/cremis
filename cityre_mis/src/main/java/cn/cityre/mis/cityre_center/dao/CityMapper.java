package cn.cityre.mis.cityre_center.dao;

import cn.cityre.mis.cityre_center.entity.query.CityQuery;
import cn.cityre.mis.cityre_center.model.City;

import java.util.List;

public interface CityMapper {
    int deleteByPrimaryKey(String cityCode);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String cityCode);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    List<City> selectList(CityQuery cityQuery);
}
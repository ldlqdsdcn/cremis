package cn.cityre.mis.city.dao;

import cn.cityre.mis.city.model.District;

import java.util.List;
import java.util.Map;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKeyWithBLOBs(District record);

    int updateByPrimaryKey(District record);

    List<District> selectList(Map<String,Object> param);
}
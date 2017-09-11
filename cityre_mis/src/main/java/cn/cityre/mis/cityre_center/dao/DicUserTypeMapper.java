package cn.cityre.mis.cityre_center.dao;

import cn.cityre.mis.cityre_center.model.DicUserType;

import java.util.List;
import java.util.Map;

public interface DicUserTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicUserType record);

    int insertSelective(DicUserType record);

    DicUserType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicUserType record);

    int updateByPrimaryKeyWithBLOBs(DicUserType record);

    int updateByPrimaryKey(DicUserType record);

    List<DicUserType> selectList(Map<String,Object> param);
}
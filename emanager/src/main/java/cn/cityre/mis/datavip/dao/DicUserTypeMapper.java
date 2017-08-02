package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicUserType;

import java.util.List;

public interface DicUserTypeMapper {
    List<DicUserType> selectByTypeTitle(String typeTitle);

    int deleteByPrimaryKey(Integer id);

    int insert(DicUserType record);

    int insertSelective(DicUserType record);

    DicUserType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicUserType record);

    int updateByPrimaryKeyWithBLOBs(DicUserType record);

    int updateByPrimaryKey(DicUserType record);
}
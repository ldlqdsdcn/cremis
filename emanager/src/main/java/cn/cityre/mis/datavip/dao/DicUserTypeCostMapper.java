package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicUserTypeCost;

public interface DicUserTypeCostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicUserTypeCost record);

    int insertSelective(DicUserTypeCost record);

    DicUserTypeCost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicUserTypeCost record);

    int updateByPrimaryKeyWithBLOBs(DicUserTypeCost record);

    int updateByPrimaryKey(DicUserTypeCost record);
}
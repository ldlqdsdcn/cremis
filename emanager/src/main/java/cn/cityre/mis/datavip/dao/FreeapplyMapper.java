package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.FreeApply;

public interface FreeapplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FreeApply record);

    int insertSelective(FreeApply record);

    FreeApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreeApply record);

    int updateByPrimaryKey(FreeApply record);
}
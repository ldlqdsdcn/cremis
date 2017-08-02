package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicModule;

public interface DicModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicModule record);

    int insertSelective(DicModule record);

    DicModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicModule record);

    int updateByPrimaryKey(DicModule record);
}
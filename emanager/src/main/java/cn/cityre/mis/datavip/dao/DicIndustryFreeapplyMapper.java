package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicIndustryFreeApply;

public interface DicIndustryFreeapplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicIndustryFreeApply record);

    int insertSelective(DicIndustryFreeApply record);

    DicIndustryFreeApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicIndustryFreeApply record);

    int updateByPrimaryKey(DicIndustryFreeApply record);
}
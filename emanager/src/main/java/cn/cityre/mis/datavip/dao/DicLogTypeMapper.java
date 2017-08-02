package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicLogType;

public interface DicLogTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicLogType record);

    int insertSelective(DicLogType record);

    DicLogType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicLogType record);

    int updateByPrimaryKey(DicLogType record);
}
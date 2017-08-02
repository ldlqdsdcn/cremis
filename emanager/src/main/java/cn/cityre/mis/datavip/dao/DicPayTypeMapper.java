package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicPayType;

public interface DicPayTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicPayType record);

    int insertSelective(DicPayType record);

    DicPayType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicPayType record);

    int updateByPrimaryKey(DicPayType record);
}
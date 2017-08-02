package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicPostType;

public interface DicPostTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicPostType record);

    int insertSelective(DicPostType record);

    DicPostType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicPostType record);

    int updateByPrimaryKey(DicPostType record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicOpenToken;

public interface DicOpentokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicOpenToken record);

    int insertSelective(DicOpenToken record);

    DicOpenToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicOpenToken record);

    int updateByPrimaryKey(DicOpenToken record);
}
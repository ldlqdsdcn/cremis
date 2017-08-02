package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserAvmser;

public interface UserAvmserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAvmser record);

    int insertSelective(UserAvmser record);

    UserAvmser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAvmser record);

    int updateByPrimaryKey(UserAvmser record);
}
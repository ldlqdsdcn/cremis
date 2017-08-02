package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UidToken;

public interface UidTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UidToken record);

    int insertSelective(UidToken record);

    UidToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UidToken record);

    int updateByPrimaryKey(UidToken record);
}
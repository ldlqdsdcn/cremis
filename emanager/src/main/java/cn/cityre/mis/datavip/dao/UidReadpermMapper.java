package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UidReadPerm;

public interface UidReadpermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UidReadPerm record);

    int insertSelective(UidReadPerm record);

    UidReadPerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UidReadPerm record);

    int updateByPrimaryKey(UidReadPerm record);
}
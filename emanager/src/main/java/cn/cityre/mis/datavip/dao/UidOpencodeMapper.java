package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UidOpenCode;

public interface UidOpencodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UidOpenCode record);

    int insertSelective(UidOpenCode record);

    UidOpenCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UidOpenCode record);

    int updateByPrimaryKey(UidOpenCode record);
}
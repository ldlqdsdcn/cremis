package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.IpWhiteList;

public interface IpWhitelistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IpWhiteList record);

    int insertSelective(IpWhiteList record);

    IpWhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpWhiteList record);

    int updateByPrimaryKey(IpWhiteList record);
}
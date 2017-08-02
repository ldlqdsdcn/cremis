package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserInviteReg;

public interface UserInviteRegMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInviteReg record);

    int insertSelective(UserInviteReg record);

    UserInviteReg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInviteReg record);

    int updateByPrimaryKey(UserInviteReg record);
}
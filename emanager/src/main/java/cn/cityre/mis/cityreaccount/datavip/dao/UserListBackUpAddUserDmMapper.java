package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.UserListBackUpAddUserDm;

public interface UserListBackUpAddUserDmMapper {
    int insert(UserListBackUpAddUserDm record);

    int insertSelective(UserListBackUpAddUserDm record);
}
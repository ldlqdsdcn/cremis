package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.UserListBackUp;

public interface UserListBackUpMapper {
    int insert(UserListBackUp record);

    int insertSelective(UserListBackUp record);
}
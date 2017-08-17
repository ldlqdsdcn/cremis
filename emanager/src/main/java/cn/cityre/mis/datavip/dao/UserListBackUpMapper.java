package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserListBackUp;

public interface UserListBackUpMapper {
    int insert(UserListBackUp record);

    int insertSelective(UserListBackUp record);
}
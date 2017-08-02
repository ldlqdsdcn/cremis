package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserListBack;

public interface UserListBackMapper {
    int insert(UserListBack record);

    int insertSelective(UserListBack record);
}
package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserList;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListMapper {

    PageMyBatis<UserList> selectUserInfoByPage(PagingCriteria pagingCriteria);

    int deleteByPrimaryKey(Integer id);

    int insert(UserList record);

    int insertSelective(UserList record);

    UserList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserList record);

    int updateByPrimaryKey(UserList record);
}
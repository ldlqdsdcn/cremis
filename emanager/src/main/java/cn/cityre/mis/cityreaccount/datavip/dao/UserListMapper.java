package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.UserList;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserListMapper {

    PageMyBatis<UserList> selectUserInfoByPage(Map<String, Object> map);

    List<UserList> selectExportInfo(Map<String,Object> map);

    String selectUserPassword(String suid);

    UserList selectBySuid(String suid);

    int deleteByPrimaryKey(Integer id);

    int insert(UserList record);

    int insertSelective(UserList record);

    UserList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserList record);

    int updateByPrimaryKey(UserList record);
}
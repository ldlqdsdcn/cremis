package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.model.UserPrivileges;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPrivilegesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPrivileges record);

    int insertSelective(UserPrivileges record);

    UserPrivileges selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPrivileges record);

    int updateByPrimaryKey(UserPrivileges record);

    List<UserPrivileges> getUserPrivilegeList(String unionUid);

    void deleteList(@Param("unionuid") String unionUid, @Param("repositoryIdList") List<Integer> repositoryIdList);

    int countPrivilegesByRepIdAndUnionUid(@Param("unionuid") String unionUid, @Param("repositoryId") Integer repositoryId);
}
package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.model.GroupPrivileges;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GroupPrivilegesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupPrivileges record);

    int insertSelective(GroupPrivileges record);

    GroupPrivileges selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupPrivileges record);

    int updateByPrimaryKey(GroupPrivileges record);

    List<GroupPrivileges> getGroupPrivilegeList(Integer groupId);

    void deleteList(@Param("groupId") Integer groupId, @Param("repositoryIdList") List<Integer> repositoryIdList);

    void deleteListByRepositoryId(@Param("repositoryId") Integer repositoryId);
    int countPrivilegesByRepIdAndGroupId(@Param("groupId") Integer groupId, @Param("repositoryId") Integer repositoryId);
}
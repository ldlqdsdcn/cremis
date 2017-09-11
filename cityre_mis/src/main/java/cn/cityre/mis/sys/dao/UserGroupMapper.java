package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.model.UserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);

    List<UserGroup> getUserGroupByUnionUid(@Param("unionUid") String unionUid);

    void deleteList(@Param("unionUid") String unionUid, @Param("groupList") List<Integer> groupList);

    int countByUnionUidAndGroupId(@Param("unionUid") String unionUid, @Param("groupId") Integer groupId);
}
package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.entity.query.GroupQuery;
import cn.cityre.mis.sys.model.Group;

import java.util.List;
import java.util.Map;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List<Group> selectList(GroupQuery param);
}
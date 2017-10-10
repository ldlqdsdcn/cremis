package cn.cityre.mis.sys.service;

import cn.cityre.mis.sys.entity.bo.GroupCityBo;
import cn.cityre.mis.sys.entity.query.GroupQuery;
import cn.cityre.mis.sys.entity.union.GroupRepositoryUnion;
import cn.cityre.mis.sys.model.Group;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/31 14:38.
 */
public interface GroupService {
    List<Group> getGroupList(GroupQuery groupQuery);

    Group getGroup(Integer id);

    List<GroupRepositoryUnion> getGroupRepositoryList(Integer groupId);

    void saveGroup(Group group, List<GroupRepositoryUnion> groupRepositoryUnions);

    void deleteGroupByIds(List<Integer> ids);

    GroupCityBo getGroupCityBo(Integer id);

    void saveGroupCities(Integer groupId, List<String> cities, String createdBy);

}

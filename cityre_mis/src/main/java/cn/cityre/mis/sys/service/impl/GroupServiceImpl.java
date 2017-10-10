package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.center.service.CityService;
import cn.cityre.mis.sys.dao.GroupCityMapper;
import cn.cityre.mis.sys.dao.GroupMapper;
import cn.cityre.mis.sys.dao.GroupPrivilegesMapper;
import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.entity.bo.GroupCityBo;
import cn.cityre.mis.sys.entity.bo.ProvinceBo;
import cn.cityre.mis.sys.entity.query.GroupCityQuery;
import cn.cityre.mis.sys.entity.query.GroupQuery;
import cn.cityre.mis.sys.entity.union.GroupRepositoryUnion;
import cn.cityre.mis.sys.model.Group;
import cn.cityre.mis.sys.model.GroupCity;
import cn.cityre.mis.sys.model.GroupPrivileges;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.service.GroupService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 刘大磊 on 2017/8/31 14:38.
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupCityMapper groupCityMapper;
    @Autowired
    private GroupPrivilegesMapper groupPrivilegesMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;
    @Autowired
    private CityService cityService;

    @Override
    public List<Group> getGroupList(GroupQuery groupQuery) {
        return groupMapper.selectList(groupQuery);
    }

    @Override
    public Group getGroup(Integer id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GroupRepositoryUnion> getGroupRepositoryList(Integer groupId) {
        List<GroupPrivileges> groupPrivilegesList = null;
        if (groupId == null) {
            groupPrivilegesList = new ArrayList<>();
        } else {
            groupPrivilegesList = groupPrivilegesMapper.getGroupPrivilegeList(groupId);
        }

        List<Repository> repositoryList = repositoryMapper.getRepositoryList(null, new RowBounds());

        List<GroupRepositoryUnion> groupRepositoryUnionList = new ArrayList<>();
        for (Repository repository : repositoryList) {
            GroupRepositoryUnion groupRepositoryUnion = new GroupRepositoryUnion();
            for (GroupPrivileges gp : groupPrivilegesList) {
                if (gp.getRepositoryId().equals(repository.getId())) {
                    groupRepositoryUnion.setChecked(true);
                    groupRepositoryUnion.setId(gp.getId());
                    groupRepositoryUnion.setCreated(gp.getCreated());
                    groupRepositoryUnion.setCreatedby(gp.getCreatedby());
                    break;
                }
            }
            groupRepositoryUnion.setCreated(null);
            groupRepositoryUnion.setGroupId(groupId);
            groupRepositoryUnion.setRepositoryId(repository.getId());
            groupRepositoryUnion.setRepositoryNo(repository.getNo());
            groupRepositoryUnion.setRepositoryName(repository.getName());
            groupRepositoryUnionList.add(groupRepositoryUnion);
        }
        return groupRepositoryUnionList;
    }

    public void saveGroup(Group group, List<GroupRepositoryUnion> groupRepositoryUnions) {
        boolean isNew = group.getId() == null;

        List<Integer> repositoryIds = groupRepositoryUnions.stream().map(GroupRepositoryUnion::getRepositoryId).collect(Collectors.toList());
        if (!isNew) {
            groupPrivilegesMapper.deleteList(group.getId(), repositoryIds);
        } else {
            group.setCreated(group.getUpdated());
            group.setCreatedby(group.getUpdatedby());
        }
        if (isNew) {
            groupMapper.insert(group);
        } else {
            groupMapper.updateByPrimaryKeySelective(group);
        }
        for (GroupRepositoryUnion groupRepositoryUnion : groupRepositoryUnions) {
            Integer repId = groupRepositoryUnion.getRepositoryId();
            Integer groupId = group.getId();
            if (!isNew) {
                int count = groupPrivilegesMapper.countPrivilegesByRepIdAndGroupId(groupId, repId);
                if (count > 0) {
                    continue;
                }
            }
            GroupPrivileges groupPrivileges = new GroupPrivileges();
            groupPrivileges.setCreated(group.getUpdated());
            groupPrivileges.setCreatedby(group.getUpdatedby());
            groupPrivileges.setGroupId(groupId);
            groupPrivileges.setRepositoryId(repId);
            groupPrivilegesMapper.insert(groupPrivileges);
        }

    }

    @Override
    public void deleteGroupByIds(List<Integer> ids) {
        for (Integer id : ids) {
            groupPrivilegesMapper.deleteList(id, null);
            groupMapper.deleteByPrimaryKey(id);

        }
    }

    @Override
    public GroupCityBo getGroupCityBo(Integer id) {
        GroupCityBo groupCityBo = new GroupCityBo();
        Group group = groupMapper.selectByPrimaryKey(id);
        GroupCityQuery groupCityQuery = new GroupCityQuery();
        groupCityQuery.setGroupId(id);
        List<GroupCity> groupCityList = groupCityMapper.selectList(groupCityQuery);
        List<String> cityIdList = groupCityList.stream().map(GroupCity::getCityCode).collect(Collectors.toList());
        List<ProvinceBo> provinceBoList = cityService.getProvinceBoList(cityIdList);
        groupCityBo.setGroupId(group.getId());
        groupCityBo.setGroupName(group.getName());
        groupCityBo.setProvinceBoList(provinceBoList);
        return groupCityBo;
    }

    @Override
    public void saveGroupCities(Integer groupId, List<String> cities, String createdBy) {
        if (groupId == null) {
            throw new IllegalArgumentException("groupId不允许为空");
        }
        Date now = new Date();
        GroupCityQuery groupCityQuery = new GroupCityQuery();
        groupCityQuery.setCities(cities);
        groupCityQuery.setGroupId(groupId);
        groupCityMapper.deleteList(groupCityQuery);
        for (String city : cities) {
            int count = groupCityMapper.countGroupCity(groupId, city);
            if (count == 0) {
                GroupCity groupCity = new GroupCity();
                groupCity.setCityCode(city);
                groupCity.setGroupId(groupId);
                groupCity.setCreated(now);
                groupCity.setCreatedby(createdBy);
                groupCityMapper.insert(groupCity);
            }
        }
    }
}

package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.entity.query.GroupCityQuery;
import cn.cityre.mis.sys.model.GroupCity;

import java.util.List;

public interface GroupCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupCity record);

    int insertSelective(GroupCity record);

    GroupCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupCity record);

    int updateByPrimaryKey(GroupCity record);

    /**
     *
     * @param groupCityQuery
     * @return
     */
    List<GroupCity> selectList(GroupCityQuery groupCityQuery);
}
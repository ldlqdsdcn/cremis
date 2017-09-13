package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.entity.query.UserCityQuery;
import cn.cityre.mis.sys.model.UserCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCity record);

    int insertSelective(UserCity record);

    UserCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCity record);

    int updateByPrimaryKey(UserCity record);

    List<UserCity> selectList(UserCityQuery userCityQuery);

    void deleteList(UserCityQuery userCityQuery);

    int countUserCity(@Param("unionUid") String unionUid, @Param("cityCode") String cityCode);

    /**
     * 获取所有可访问的城市列表
     *
     * @param unionUid
     * @return
     */
    List<String> selectCityCode(@Param("unionUid") String unionUid);
}
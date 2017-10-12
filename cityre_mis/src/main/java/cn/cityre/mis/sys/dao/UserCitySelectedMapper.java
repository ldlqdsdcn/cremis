package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.model.UserCitySelected;
import org.apache.ibatis.annotations.Param;

public interface UserCitySelectedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCitySelected record);

    int insertSelective(UserCitySelected record);

    UserCitySelected selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCitySelected record);

    int updateByPrimaryKey(UserCitySelected record);

    /*
     * 根据用户unionuid获取城市
     */
    UserCitySelected selectByUnionUid(@Param("unionuid") String unionuid);
}
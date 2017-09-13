package cn.cityre.mis.center.dao;

import cn.cityre.mis.center.entity.query.ProvinceQuery;
import cn.cityre.mis.center.model.Province;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    List<Province> selectList(ProvinceQuery provinceQuery);

    List<Province> selectProvinceByCityCodes(@Param("cityCodeIn") List<String> cityCodes);
}
package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.DicUserTypeCost;
import org.springframework.stereotype.Repository;

@Repository
public interface DicUserTypeCostMapper {
    DicUserTypeCost selectByUserType(String userType);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(DicUserTypeCost record);

    DicUserTypeCost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicUserTypeCost record);
}
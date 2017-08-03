package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicUserType;
import cn.cityre.mis.datavip.entity.DicUserTypeCost;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicUserTypeMapper {


    List<DicUserType> selectByTypeTitle(String typeTitle);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(DicUserType record);

    DicUserType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicUserType record);
}
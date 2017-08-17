package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.DicUserType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicUserTypeMapper {
    List<DicUserType> selectByType();

    List<DicUserType> selectByTypeTitle(String typeTitle);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(DicUserType record);

    DicUserType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicUserType record);
}
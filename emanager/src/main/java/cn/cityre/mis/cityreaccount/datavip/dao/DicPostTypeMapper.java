package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.DicPostType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicPostTypeMapper {
    List<DicPostType> selectAllPostType();

    void deleteByPrimaryKey(Integer id);

    void insert(DicPostType dicPostType);

    DicPostType selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(DicPostType dicPostType);
}
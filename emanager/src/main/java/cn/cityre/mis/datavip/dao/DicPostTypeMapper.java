package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicPostType;
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
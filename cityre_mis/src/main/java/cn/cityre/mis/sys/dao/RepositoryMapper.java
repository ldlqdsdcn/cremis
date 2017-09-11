package cn.cityre.mis.sys.dao;

import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.pagination.dto.PageMyBatis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepositoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Repository record);

    int insertSelective(Repository record);

    Repository selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repository record);

    int updateByPrimaryKey(Repository record);

    Set<String> getUserRepositories(@Param("unionUid") String unionUid);

    Set<Integer> getUserRepositoryIds(@Param("unionUid")String unionUid);

    Set<String> getAllRepositories();
    List<Repository> getRepositoryList(RepositoryQuery param);
    PageMyBatis<Repository> getRepositoryList(RepositoryQuery param, RowBounds rowBounds);

    int checkNoExist(@Param("no") String no, @Param("id") Integer id);

}
package cn.cityre.mis.sys.service;

import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Repository;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/28 12:28.
 */
public interface RepositoryService {
    /**
     * 不分页查询
     * @param repositoryQuery
     * @return
     */
    List<Repository> getList(RepositoryQuery repositoryQuery);

    /**
     * 分页查询
     * @param repositoryQuery
     * @return
     */
    List<Repository> getRepositoryList(RepositoryQuery repositoryQuery);

    void removeRepositoryList(Integer[] ids);

    Repository saveRepository(Repository repository);

    boolean existRepository(String no, Integer id);

    Repository getRepository(Integer id);
}

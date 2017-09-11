package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.service.RepositoryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by 刘大磊 on 2017/8/28 12:28.
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private RepositoryMapper repositoryMapper;

    public List<Repository> getRepositoryList(RepositoryQuery repositoryQuery) {
        return repositoryMapper.getRepositoryList(repositoryQuery, new RowBounds(repositoryQuery.getStartRow(), repositoryQuery.getRows()));
    }
    public List<Repository> getList(RepositoryQuery repositoryQuery)
    {
        return repositoryMapper.getRepositoryList(repositoryQuery);
    }
    @Override
    public void removeRepositoryList(Integer[] ids) {
        if (ids != null) {
            for (Integer id : ids) {
                repositoryMapper.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public Repository saveRepository(Repository repository) {
        if (repository.getId() == null) {
            repository.setCreated(repository.getUpdated());
            repository.setCreatedby(repository.getUpdatedby());
            repositoryMapper.insert(repository);
        } else {
            repositoryMapper.updateByPrimaryKeySelective(repository);
        }
        return repository;
    }

    public boolean existRepository(String no, Integer id) {
        int count = repositoryMapper.checkNoExist(no, id);
        if (count > 0) return true;
        return false;
    }
}

package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.sys.dao.GroupPrivilegesMapper;
import cn.cityre.mis.sys.dao.MenuMapper;
import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.dao.UserPrivilegesMapper;
import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.service.RepositoryService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/28 12:28.
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private RepositoryMapper repositoryMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private GroupPrivilegesMapper groupPrivilegesMapper;
    @Autowired
    private UserPrivilegesMapper userPrivilegesMapper;

    public Page<Repository> getRepositoryList(RepositoryQuery repositoryQuery) {
        return repositoryMapper.getRepositoryList(repositoryQuery, new RowBounds(repositoryQuery.getStartRow(), repositoryQuery.getRows()));
    }

    public List<Repository> getList(RepositoryQuery repositoryQuery) {
        return repositoryMapper.getRepositoryList(repositoryQuery);
    }

    @Override
    public void removeRepositoryList(Integer[] ids) {
        if (ids != null) {
            for (Integer id : ids) {
                groupPrivilegesMapper.deleteListByRepositoryId(id);
                userPrivilegesMapper.deleteByRepositoryId(id);
                menuMapper.updateRepositoryList(id);
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
        return count > 0;
    }

    @Override
    public Repository getRepository(Integer id) {
        return repositoryMapper.selectByPrimaryKey(id);
    }
}

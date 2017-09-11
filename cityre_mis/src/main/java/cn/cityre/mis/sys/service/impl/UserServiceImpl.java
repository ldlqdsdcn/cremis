package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.account.dao.AccountUserMapper;
import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.model.AccountUser;
import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.service.UserService;
import cn.cityre.mis.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by 刘大磊 on 2017/8/23 14:36.
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${admin.account.name}")
    private String adminAccount;
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;

    /**
     * @param username
     * @return
     * @see UserService#getUserByUsername(String)
     */
    public AccountUser getUserByUsername(String username) {
        AccountUserQuery param = new AccountUserQuery();
        param.setUserId(username);
        List<AccountUser> accountUserList = accountUserMapper.selectList(param);
        if (accountUserList.size() > 0) {
            return accountUserList.get(0);
        }
        return null;
    }

    @Cacheable(value = "userContentCache", key = "#unionUid")
    @Transactional
    @Override
    public Set<String> getUserPrivileges(String unionUid) {
        if (StringUtil.isEmpty(unionUid)) {
            throw new IllegalArgumentException("unionUid不允许为空");
        }
        if (adminAccount.equals(unionUid)) {
            return repositoryMapper.getAllRepositories();
        }
        return repositoryMapper.getUserRepositories(unionUid);
    }
}

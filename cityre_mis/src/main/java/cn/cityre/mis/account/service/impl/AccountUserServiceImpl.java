package cn.cityre.mis.account.service.impl;

import cn.cityre.mis.account.dao.AccountUserMapper;
import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.entity.union.UserGroupUnion;
import cn.cityre.mis.account.entity.union.UserRepositoryUnion;
import cn.cityre.mis.account.model.AccountUser;
import cn.cityre.mis.account.service.AccountUserService;
import cn.cityre.mis.sys.dao.GroupMapper;
import cn.cityre.mis.sys.dao.RepositoryMapper;
import cn.cityre.mis.sys.dao.UserGroupMapper;
import cn.cityre.mis.sys.dao.UserPrivilegesMapper;
import cn.cityre.mis.sys.entity.query.GroupQuery;
import cn.cityre.mis.sys.entity.query.RepositoryQuery;
import cn.cityre.mis.sys.model.Group;
import cn.cityre.mis.sys.model.Repository;
import cn.cityre.mis.sys.model.UserGroup;
import cn.cityre.mis.sys.model.UserPrivileges;
import cn.cityre.mis.util.StringUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 刘大磊 on 2017/8/23 16:10.
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private UserPrivilegesMapper userPrivilegesMapper;
    @Autowired
    private RepositoryMapper repositoryMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Page<AccountUser> getAccountUser(int firstRow, int rows) {
        return accountUserMapper.selectList(null, new RowBounds(firstRow, rows));
    }

    @Override
    public Page<AccountUser> getAccountUserList(AccountUserQuery accountUserQuery) {
        return accountUserMapper.selectList(accountUserQuery, new RowBounds(accountUserQuery.getStartRow(), accountUserQuery.getRows()));
    }

    @Override
    public AccountUser getAccountUser(Integer userId) {
        return accountUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<UserRepositoryUnion> getAccountPrivileges(String unionUid) {
        List<UserRepositoryUnion> userRepositoryUnionList = new ArrayList<>();
        List<UserPrivileges> userPrivilegesList = null;
        if (unionUid == null) {
            userPrivilegesList = new ArrayList<>();
        } else {
            userPrivilegesList = userPrivilegesMapper.getUserPrivilegeList(unionUid);
        }
        RepositoryQuery repositoryQuery = new RepositoryQuery();
        repositoryQuery.setIsactive("Y");
        List<Repository> repositoryList = repositoryMapper.getRepositoryList(repositoryQuery, new RowBounds());
        for (Repository repository : repositoryList) {
            UserRepositoryUnion groupRepositoryUnion = new UserRepositoryUnion();
            for (UserPrivileges gp : userPrivilegesList) {
                if (gp.getRepositoryId().equals(repository.getId())) {
                    groupRepositoryUnion.setChecked(true);
                    groupRepositoryUnion.setId(gp.getId());
                    groupRepositoryUnion.setCreated(gp.getCreated());
                    groupRepositoryUnion.setCreatedby(gp.getCreatedby());
                    break;
                }
            }
            groupRepositoryUnion.setCreated(null);
            groupRepositoryUnion.setUnionUid(unionUid);
            groupRepositoryUnion.setRepositoryId(repository.getId());
            groupRepositoryUnion.setRepositoryNo(repository.getNo());
            groupRepositoryUnion.setRepositoryName(repository.getName());
            userRepositoryUnionList.add(groupRepositoryUnion);
        }
        return userRepositoryUnionList;
    }

    public List<UserGroupUnion> getUserGroupUnionList(String unionUid) {
        List<UserGroupUnion> userGroupUnionList = new ArrayList<>();
        List<UserGroup> userGroups = new ArrayList<>();
        if (StringUtil.isNotEmpty(unionUid)) {
            userGroups = userGroupMapper.getUserGroupByUnionUid(unionUid);
        }
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setIsactive("Y");
        List<Group> groupList = groupMapper.selectList(groupQuery);
        for (Group group : groupList) {
            UserGroupUnion userGroupUnion = new UserGroupUnion();
            boolean check = false;
            for (UserGroup userGroup : userGroups) {
                if (userGroup.getGroupId().equals(group.getId())) {
                    check = true;
                    userGroupUnion.setUserGroupId(userGroup.getId());
                    break;
                }


            }
            if (check) {
                userGroupUnion.setChecked(true);
            }
            userGroupUnion.setGroupId(group.getId());
            userGroupUnion.setName(group.getName());
            userGroupUnion.setNo(group.getNo());
            userGroupUnionList.add(userGroupUnion);
        }
        return userGroupUnionList;
    }

    /**
     * @param accountUser         账户
     * @param repositoryUnionList 授权点
     * @param groupUnionList      用户组
     * @param unionUid            修改者
     */
    public void saveAccount(AccountUser accountUser, List<UserRepositoryUnion> repositoryUnionList, List<UserGroupUnion> groupUnionList, String unionUid) {
        Date now = new Date();
        accountUser.setUpdatetime(now);
        accountUserMapper.updateByPrimaryKeySelective(accountUser);
        List<Integer> repositoryIdList = repositoryUnionList.stream().map(e -> e.getRepositoryId()).collect(Collectors.toList());
        List<Integer> groupIdList = groupUnionList.stream().map(e -> e.getGroupId()).collect(Collectors.toList());
        userPrivilegesMapper.deleteList(accountUser.getUnionuid(), repositoryIdList);
        userGroupMapper.deleteList(accountUser.getUnionuid(), groupIdList);
        for (Integer repositoryId : repositoryIdList) {
            int count = userPrivilegesMapper.countPrivilegesByRepIdAndUnionUid(accountUser.getUnionuid(), repositoryId);
            if (count == 0) {
                UserPrivileges userPrivileges = new UserPrivileges();
                userPrivileges.setCreated(now);
                userPrivileges.setCreatedby(unionUid);
                userPrivileges.setRepositoryId(repositoryId);
                userPrivileges.setUnionuid(accountUser.getUnionuid());
                userPrivilegesMapper.insert(userPrivileges);
            }
        }
        for (Integer groupId : groupIdList) {
            int count = userGroupMapper.countByUnionUidAndGroupId(accountUser.getUnionuid(), groupId);
            if (count == 0) {
                UserGroup userGroup = new UserGroup();
                userGroup.setCreated(now);
                userGroup.setCreatedby(unionUid);
                userGroup.setGroupId(groupId);
                userGroup.setUnionuid(accountUser.getUnionuid());
                userGroupMapper.insert(userGroup);
            }
        }

    }
}

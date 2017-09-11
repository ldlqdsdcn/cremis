package cn.cityre.mis.account.entity.vo;

import cn.cityre.mis.account.entity.union.UserGroupUnion;
import cn.cityre.mis.account.entity.union.UserRepositoryUnion;
import cn.cityre.mis.account.model.AccountUser;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/4 15:04.
 */
public class AccountUserVo {
    private AccountUser accountUser;
    /**
     * 用户权限
     */
    private List<UserRepositoryUnion> userRepositoryUnionList;
    /**
     * 用户所属用户组
     */
    private List<UserGroupUnion> userGroupUnionList;

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public List<UserRepositoryUnion> getUserRepositoryUnionList() {
        return userRepositoryUnionList;
    }

    public void setUserRepositoryUnionList(List<UserRepositoryUnion> userRepositoryUnionList) {
        this.userRepositoryUnionList = userRepositoryUnionList;
    }

    public List<UserGroupUnion> getUserGroupUnionList() {
        return userGroupUnionList;
    }

    public void setUserGroupUnionList(List<UserGroupUnion> userGroupUnionList) {
        this.userGroupUnionList = userGroupUnionList;
    }
}

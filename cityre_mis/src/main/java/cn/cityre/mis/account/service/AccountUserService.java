package cn.cityre.mis.account.service;

import cn.cityre.mis.account.entity.query.AccountUserQuery;
import cn.cityre.mis.account.entity.union.UserGroupUnion;
import cn.cityre.mis.account.entity.union.UserRepositoryUnion;
import cn.cityre.mis.account.model.AccountUser;
import org.mybatis.pagination.dto.PageMyBatis;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/23 16:10.U
 */
public interface AccountUserService {
    /**
     * 获取 账户信息分页查询
     * @param firstRow
     * @param rows
     * @return
     */
    PageMyBatis<AccountUser> getAccountUser(int firstRow,int rows);

    PageMyBatis<AccountUser> getAccountUserList(AccountUserQuery accountUserQuery);
    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    AccountUser getAccountUser(Integer userId);

    List<UserRepositoryUnion> getAccountPrivileges(String unionUid);

    List<UserGroupUnion> getUserGroupUnionList(String unionUid);

    void saveAccount(AccountUser accountUser,List<UserRepositoryUnion> repositoryUnionList,List<UserGroupUnion> groupUnionList,String unionUid);

}

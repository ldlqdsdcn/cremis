package cn.cityre.mis.sys.service;

import cn.cityre.mis.account.model.AccountUser;

import java.util.Set;

/**
 * Created by 刘大磊 on 2017/8/23 14:35.
 */
public interface UserService {
    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    AccountUser getUserByUsername(String username);

    /**
     *
     * 根据unionUid获取用户拥有的权限
     * @return
     */
    Set<String> getUserPrivileges(String unionUid);
}

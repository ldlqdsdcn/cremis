package cn.cityre.mis.account.entity.query;

import cn.cityre.mis.core.entity.query.BaseQuery;

/**
 * Created by 刘大磊 on 2017/9/4 10:52.
 */
public class AccountUserQuery extends BaseQuery {
    private String userId;
    private String useridLike;
    private String nicknameLike;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUseridLike() {
        return useridLike;
    }

    public void setUseridLike(String useridLike) {
        this.useridLike = useridLike;
    }

    public String getNicknameLike() {
        return nicknameLike;
    }

    public void setNicknameLike(String nicknameLike) {
        this.nicknameLike = nicknameLike;
    }
}

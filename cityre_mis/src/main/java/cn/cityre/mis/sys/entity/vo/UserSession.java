package cn.cityre.mis.sys.entity.vo;

/**
 * Created by 刘大磊 on 2017/8/30 17:54.
 * 用户session
 */
public class UserSession {
    private String unionUid;
    private String name;
    private String userId;
    private String token;

    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

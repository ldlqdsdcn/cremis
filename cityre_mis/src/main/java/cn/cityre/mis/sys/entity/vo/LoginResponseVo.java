package cn.cityre.mis.sys.entity.vo;

/**
 * Created by 刘大磊 on 2017/8/31 9:48.
 */
public class LoginResponseVo {
    private String status;
    private String userToken;
    private String unionUid;
    private String userId;
    private String nickName;
    private Integer isVerified;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }
}

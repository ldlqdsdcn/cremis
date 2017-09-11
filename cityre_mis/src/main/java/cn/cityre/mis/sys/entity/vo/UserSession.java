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
    /*
     *当前城市
     */
    private String currentCityCode;
    /**
     * 当前省份
     */
    private String currentProvinceCode;
    /**
     * 当前城市名
     */
    private String cityName;

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

    public String getCurrentCityCode() {
        return currentCityCode;
    }

    public void setCurrentCityCode(String currentCityCode) {
        this.currentCityCode = currentCityCode;
    }

    public String getCurrentProvinceCode() {
        return currentProvinceCode;
    }

    public void setCurrentProvinceCode(String currentProvinceCode) {
        this.currentProvinceCode = currentProvinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

package cn.cityre.mis.sys.entity.query;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/12 15:53.
 */
public class UserCityQuery {

    private String unionUid;
    private String cityCode;
    private List<String> cities;
    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}

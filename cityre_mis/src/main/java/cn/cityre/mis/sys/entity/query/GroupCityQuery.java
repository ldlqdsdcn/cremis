package cn.cityre.mis.sys.entity.query;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/12 15:53.
 */
public class GroupCityQuery {
    private Integer groupId;

    private String cityCode;
    private List<String> cities;
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

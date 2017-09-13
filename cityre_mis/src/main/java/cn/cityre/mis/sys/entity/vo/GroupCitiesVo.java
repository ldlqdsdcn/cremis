package cn.cityre.mis.sys.entity.vo;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/13 14:33.
 */
public class GroupCitiesVo {
    private Integer groupId;
    private List<String> cities;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}

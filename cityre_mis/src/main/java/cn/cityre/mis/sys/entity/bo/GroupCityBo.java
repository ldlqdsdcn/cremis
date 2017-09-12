package cn.cityre.mis.sys.entity.bo;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/12 16:32.
 */
public class GroupCityBo {
    private Integer groupId;
    private String groupName;
    private List<ProvinceBo> provinceBoList;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ProvinceBo> getProvinceBoList() {
        return provinceBoList;
    }

    public void setProvinceBoList(List<ProvinceBo> provinceBoList) {
        this.provinceBoList = provinceBoList;
    }
}

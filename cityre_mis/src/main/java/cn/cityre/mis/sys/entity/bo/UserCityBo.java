package cn.cityre.mis.sys.entity.bo;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/12 16:32.
 */
public class UserCityBo {
    private String unionUid;
    private String name;
    private List<ProvinceBo> provinceBoList;

    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProvinceBo> getProvinceBoList() {
        return provinceBoList;
    }

    public void setProvinceBoList(List<ProvinceBo> provinceBoList) {
        this.provinceBoList = provinceBoList;
    }
}

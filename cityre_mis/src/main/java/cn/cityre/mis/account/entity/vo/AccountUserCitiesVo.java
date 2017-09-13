package cn.cityre.mis.account.entity.vo;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/13 12:00.
 */
public class AccountUserCitiesVo
{
    private String unionUid;
    private List<String> cities;

    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}

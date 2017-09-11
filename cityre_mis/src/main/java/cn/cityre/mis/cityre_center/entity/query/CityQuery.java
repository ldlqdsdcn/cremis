package cn.cityre.mis.cityre_center.entity.query;

import cn.cityre.mis.core.entity.query.BaseQuery;

/**
 * Created by 刘大磊 on 2017/9/11 10:49.
 */
public class CityQuery extends BaseQuery {
    private String provinceCode;
    private String cityNameLike;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityNameLike() {
        return cityNameLike;
    }

    public void setCityNameLike(String cityNameLike) {
        this.cityNameLike = cityNameLike;
    }
}

package cn.cityre.mis.center.entity.query;

import cn.cityre.mis.core.entity.query.BaseQuery;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/11 11:31.
 */
public class CityQuery extends BaseQuery {
    private String cityNameLike;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private List<String> cityCodeIn;

    public String getCityNameLike() {
        return cityNameLike;
    }

    public void setCityNameLike(String cityNameLike) {
        this.cityNameLike = cityNameLike;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<String> getCityCodeIn() {
        return cityCodeIn;
    }

    public void setCityCodeIn(List<String> cityCodeIn) {
        this.cityCodeIn = cityCodeIn;
    }
}

package cn.cityre.mis.center.entity.query;

import cn.cityre.mis.core.entity.query.BaseQuery;
import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * Created by 刘大磊 on 2017/9/11 11:31.
 */
public class CityQuery extends BaseQuery {
    private String cityNameLike;
    private String provinceCode;

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
}

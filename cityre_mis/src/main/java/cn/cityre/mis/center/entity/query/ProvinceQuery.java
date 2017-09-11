package cn.cityre.mis.center.entity.query;

import cn.cityre.mis.core.entity.query.BaseQuery;

/**
 * Created by 刘大磊 on 2017/9/11 11:28.
 */
public class ProvinceQuery extends BaseQuery{
    private String provinceNameLike;
    private String provinceCode;

    public String getProvinceNameLike() {
        return provinceNameLike;
    }

    public void setProvinceNameLike(String provinceNameLike) {
        this.provinceNameLike = provinceNameLike;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}

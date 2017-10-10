package cn.cityre.mis.sys.entity.bo;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/12 15:59.
 * 省份信息
 */
public class ProvinceBo {
    private Integer id;

    private String code;

    private String name;

    private List<CityBo> cityBoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBo> getCityBoList() {
        return cityBoList;
    }

    public void setCityBoList(List<CityBo> cityBoList) {
        this.cityBoList = cityBoList;
    }
}

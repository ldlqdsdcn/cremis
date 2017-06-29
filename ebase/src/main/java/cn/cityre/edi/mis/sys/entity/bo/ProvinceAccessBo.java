package cn.cityre.edi.mis.sys.entity.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/6/29 8:57.
 * 访问省份列表
 */
@Getter
@Setter
public class ProvinceAccessBo {
    /**
     * 对应数据库province表 id
     */
    private Integer provinceId;
    /**
     * 对应数据库 province表provinceid
     */
    private String provinceNo;
    private String name;
    private List<CityCanAccessedBo> cityCanAccessedBoList=new ArrayList<>();

    public void addCityCanAccessedBo(CityCanAccessedBo cityCanAccessedBo)
    {
        cityCanAccessedBoList.add(cityCanAccessedBo);
    }

}

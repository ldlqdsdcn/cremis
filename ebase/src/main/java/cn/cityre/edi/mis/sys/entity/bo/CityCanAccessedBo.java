package cn.cityre.edi.mis.sys.entity.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 刘大磊 on 2017/6/29 8:48.
 * 访问城市列表
 */
@Getter
@Setter
public class CityCanAccessedBo {
    private Integer cityId;
    private String cityName;
    private boolean selected;
}

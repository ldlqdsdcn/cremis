package cn.cityre.mis.city.entity.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by 刘大磊 on 2017/7/6 16:13.
 */
@Getter
@Setter
public class CityAreaPo {
    private Integer id;
    private String areaid;
    private String area;
    private String cityid;
    private String isactive;
}

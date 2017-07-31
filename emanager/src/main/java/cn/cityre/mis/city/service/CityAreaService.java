package cn.cityre.mis.city.service;

import cn.cityre.edi.mis.base.entity.po.AreaPo;
import cn.cityre.mis.city.entity.po.CityAreaPo;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/7/6 9:25.
 */
public interface CityAreaService {
    /**
     * 获取区域信息
     * @return
     */
    List<CityAreaPo> getAreaListByPaging();

    AreaPo getArea(Integer id);

    void saveArea(AreaPo area);

    void deletes(Integer[] ids);
}

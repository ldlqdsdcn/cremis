package cn.cityre.mis.center.service;

import cn.cityre.mis.center.model.City;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/23 17:19.
 */
public interface CityService {
    /**
     * 获取所有城市列表
     * @return
     */
    List<City> getAllCityList();
}

package cn.cityre.mis.cityre_center.service;

import cn.cityre.mis.cityre_center.model.City;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/11 11:03.
 */
public interface CityService {
    List<City> getCityByProvinceCode(String provinceCode);
}

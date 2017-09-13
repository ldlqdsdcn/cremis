package cn.cityre.mis.center.service;

import cn.cityre.mis.center.model.City;
import cn.cityre.mis.center.model.Province;
import cn.cityre.mis.sys.entity.bo.ProvinceBo;

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

    /**
     * 获取所有的省份列表
     * @return
     */
    List<Province> getAllProvinceList();

    List<Province> getProvinceByUnionUid(String unionUid);

    List<City> getCityListByProvinceCodeAndUnionUid(String provinceCode, String unionUid);
    /**
     * 根据省份编码获取城市列表
     * @param provinceCode
     * @return
     */
    List<City> getCityListByProvinceCode(String provinceCode);

    City getCityByCode(String code);

    List<ProvinceBo> getProvinceBoList(List<String> cityCodeList);
}

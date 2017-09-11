package cn.cityre.mis.center.service.impl;

import cn.cityre.mis.center.dao.CityMapper;
import cn.cityre.mis.center.dao.ProvinceMapper;
import cn.cityre.mis.center.entity.query.CityQuery;
import cn.cityre.mis.center.model.City;
import cn.cityre.mis.center.model.Province;
import cn.cityre.mis.center.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/23 17:22.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private ProvinceMapper provinceMapper;

    /**
     * @return
     * @see CityService#getAllCityList()
     */
    @Override
    public List<City> getAllCityList() {
        return cityMapper.selectList(null);
    }

    /**
     * @return
     * @see CityService#getAllProvinceList()
     */
    public List<Province> getAllProvinceList() {
        return provinceMapper.selectList(null);
    }

    /**
     * @return
     * @see CityService#getCityListByProvinceCode(String)
     */
    public List<City> getCityListByProvinceCode(String provinceCode) {
        CityQuery cityQuery = new CityQuery();
        cityQuery.setProvinceCode(provinceCode);
        return cityMapper.selectList(cityQuery);
    }

    @Override
    public City getCityByCode(String code) {
        return cityMapper.selectCityByCityCode(code);
    }
}

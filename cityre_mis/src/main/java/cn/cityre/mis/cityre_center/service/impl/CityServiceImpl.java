package cn.cityre.mis.cityre_center.service.impl;

import cn.cityre.mis.cityre_center.dao.CityMapper;
import cn.cityre.mis.cityre_center.entity.query.CityQuery;
import cn.cityre.mis.cityre_center.model.City;
import cn.cityre.mis.cityre_center.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/11 11:08.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Override
    public List<City> getCityByProvinceCode(String provinceCode) {
        CityQuery cityQuery=new CityQuery();
        cityQuery.setProvinceCode(provinceCode);
        return cityMapper.selectList(cityQuery);
    }
}

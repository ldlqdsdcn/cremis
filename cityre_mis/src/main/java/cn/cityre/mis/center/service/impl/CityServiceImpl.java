package cn.cityre.mis.center.service.impl;

import cn.cityre.mis.center.dao.CityMapper;
import cn.cityre.mis.center.dao.ProvinceMapper;
import cn.cityre.mis.center.entity.query.CityQuery;
import cn.cityre.mis.center.model.City;
import cn.cityre.mis.center.model.Province;
import cn.cityre.mis.center.service.CityService;
import cn.cityre.mis.sys.entity.bo.CityBo;
import cn.cityre.mis.sys.entity.bo.ProvinceBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public List<ProvinceBo> getProvinceBoList(List<String> cityCodeList) {
        List<CityBo> cityBoList = new ArrayList<>();
        List<ProvinceBo> provinceBoList = new ArrayList<>();
        List<Province> provinceList = provinceMapper.selectList(null);
        List<City> cityList = cityMapper.selectList(null);
        for (City city : cityList) {
            CityBo cityBo = new CityBo();
            cityBo.setCode(city.getCityCode());
            cityBo.setName(city.getCityName());
            cityBo.setProvinceCode(city.getProvinceCode());
            for (String code : cityCodeList) {
                if (code.equals(city.getCityCode())) {
                    cityCodeList.remove(code);
                    cityBo.setChecked(true);
                    break;
                }
            }
            cityBoList.add(cityBo);
        }
        for (Province province : provinceList) {
            ProvinceBo provinceBo = new ProvinceBo();
            provinceBo.setCode(province.getProvinceCode());
            provinceBo.setName(province.getProvinceName());
            provinceBo.setId(province.getId());
            List<CityBo> proCityList = new ArrayList<>();
            for (int i = cityBoList.size() - 1; i >= 0; i--) {
                CityBo cityBo = cityBoList.get(i);
                if(cityBo.getProvinceCode()==null)
                {
                    continue;
                }
                if (cityBo.getProvinceCode().equals(provinceBo.getCode())) {
                    cityBoList.remove(i);
                    proCityList.add(cityBo);
                }
            }
            Collections.reverse(proCityList);
            provinceBo.setCityBoList(proCityList);
            provinceBoList.add(provinceBo);
        }
        return provinceBoList;
    }
}

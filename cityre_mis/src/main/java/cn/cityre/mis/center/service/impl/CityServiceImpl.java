package cn.cityre.mis.center.service.impl;

import cn.cityre.mis.center.dao.CityMapper;
import cn.cityre.mis.center.model.City;
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

    /**
     * @see CityService#getAllCityList()
     * @return
     */
    @Override
    public List<City> getAllCityList() {
        return cityMapper.selectList(null);
    }
}

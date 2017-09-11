package cn.cityre.mis.city.service.impl;

import cn.cityre.mis.city.dao.DistrictMapper;
import cn.cityre.mis.city.model.District;
import cn.cityre.mis.city.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/8/24 11:06.
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectList(null);
    }
}

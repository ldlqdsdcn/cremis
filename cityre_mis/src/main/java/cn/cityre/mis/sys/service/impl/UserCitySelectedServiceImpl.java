package cn.cityre.mis.sys.service.impl;

import cn.cityre.mis.center.model.City;
import cn.cityre.mis.center.service.CityService;
import cn.cityre.mis.def.SystemConsts;
import cn.cityre.mis.sys.dao.UserCitySelectedMapper;
import cn.cityre.mis.sys.model.UserCitySelected;
import cn.cityre.mis.sys.service.UserCitySelectedService;
import cn.cityre.mis.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 刘大磊 on 2017/10/12 10:57.
 */
@Service
public class UserCitySelectedServiceImpl implements UserCitySelectedService {
    @Autowired
    private UserCitySelectedMapper userCitySelectedMapper;
    @Autowired
    private CityService cityService;

    @Override
    public City getUserCitySelected(String unionuid) {
        if (StringUtil.isEmpty(unionuid)) {
            throw new IllegalArgumentException("unionuid 不允许为空");
        }
        City city;
        UserCitySelected userCitySelected = userCitySelectedMapper.selectByUnionUid(unionuid);
        if (userCitySelected == null) {
            Date now = new Date();
            userCitySelected = new UserCitySelected();
            userCitySelected.setCityCode(SystemConsts.CITY_CODE_BEI_JING);
            userCitySelected.setCreated(now);
            userCitySelected.setUpdated(now);
            userCitySelected.setUnionuid(unionuid);
            userCitySelectedMapper.insert(userCitySelected);
            city = cityService.getCityByCode(SystemConsts.CITY_CODE_BEI_JING);

        } else {
            city = cityService.getCityByCode(userCitySelected.getCityCode());
        }
        return city;
    }

    @Override
    public void saveUserCitySelected(String cityCode, String unionuid) {
        UserCitySelected temp = userCitySelectedMapper.selectByUnionUid(unionuid);
        City city = cityService.getCityByCode(cityCode);
        temp.setCityCode(cityCode);
        temp.setUpdated(new Date());
        userCitySelectedMapper.updateByPrimaryKey(temp);
    }
}

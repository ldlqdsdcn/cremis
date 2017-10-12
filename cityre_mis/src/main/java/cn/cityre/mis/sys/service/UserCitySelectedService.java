package cn.cityre.mis.sys.service;

import cn.cityre.mis.center.model.City;

/**
 * Created by 刘大磊 on 2017/10/12 10:55.
 * 用户选择城市
 */
public interface UserCitySelectedService {
    /**
     * 获取最后一次选择的城市
     *
     * @param unionuid
     * @return
     */
    City getUserCitySelected(String unionuid);

    /**
     * 保存用户城市
     *
     * @param cityCode
     * @param unionuid
     */
    void saveUserCitySelected(String cityCode, String unionuid);
}

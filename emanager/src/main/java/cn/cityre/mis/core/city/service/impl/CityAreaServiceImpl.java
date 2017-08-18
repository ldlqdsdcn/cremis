package cn.cityre.mis.core.city.service.impl;

import cn.cityre.edi.mis.base.entity.po.AreaPo;
import cn.cityre.mis.core.city.dao.CityAreaDao;
import cn.cityre.mis.core.city.entity.po.CityAreaPo;
import cn.cityre.mis.core.city.service.CityAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/7/6 9:37.
 */
@Service
@Scope("prototype")
public class CityAreaServiceImpl implements CityAreaService {
    @Autowired
    private CityAreaDao cityAreaDao;
    @Override
    public List<CityAreaPo> getAreaListByPaging() {
        return cityAreaDao.getCityAreaList();
    }

    @Override
    public AreaPo getArea(Integer id) {
        return null;
    }

    @Override
    public void saveArea(AreaPo area) {

    }

    @Override
    public void deletes(Integer[] ids) {

    }
}
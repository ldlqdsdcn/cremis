package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.DicUserTypeMapper;
import cn.cityre.mis.cityreaccount.datavip.entity.DicUserType;
import cn.cityre.mis.cityreaccount.datavip.service.DicUserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/8/3.
 */
@Service
public class DicUserTypeServiceImpl implements DicUserTypeService {
    @Autowired
    private DicUserTypeMapper dicUserTypeMapper;

    @Override
    public List<DicUserType> getExistUserTypeList() {
        List<DicUserType> list = dicUserTypeMapper.selectByType();

        return list;
    }
}

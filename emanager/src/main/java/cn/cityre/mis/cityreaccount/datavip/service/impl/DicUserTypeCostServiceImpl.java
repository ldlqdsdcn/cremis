package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.DicUserTypeCostMapper;
import cn.cityre.mis.cityreaccount.datavip.entity.DicUserTypeCost;
import cn.cityre.mis.cityreaccount.datavip.service.DicUserTypeCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cityre on 2017/8/3.
 */
@Service(value = "dicUserTypeCostService")
public class DicUserTypeCostServiceImpl implements DicUserTypeCostService {
    @Autowired
    private DicUserTypeCostMapper dicUserTypeCostMapper;
    @Override
    public DicUserTypeCost getExistByUserType(String userType) {
        DicUserTypeCost dicUserTypeCost= dicUserTypeCostMapper.selectByUserType(userType);
        return dicUserTypeCost;

    }
}

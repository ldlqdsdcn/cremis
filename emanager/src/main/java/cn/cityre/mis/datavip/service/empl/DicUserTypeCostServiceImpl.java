package cn.cityre.mis.datavip.service.empl;

import cn.cityre.mis.datavip.dao.DicUserTypeCostMapper;
import cn.cityre.mis.datavip.entity.DicUserTypeCost;
import cn.cityre.mis.datavip.service.DicUserTypeCostService;
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
        return dicUserTypeCostMapper.selectByUserType(userType);
    }
}

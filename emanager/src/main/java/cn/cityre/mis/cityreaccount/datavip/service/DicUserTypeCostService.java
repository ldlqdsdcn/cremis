package cn.cityre.mis.cityreaccount.datavip.service;

import cn.cityre.mis.cityreaccount.datavip.entity.DicUserTypeCost;

/**
 * Created by cityre on 2017/8/3.
 */
public interface DicUserTypeCostService {
    DicUserTypeCost getExistByUserType(String userType);
}

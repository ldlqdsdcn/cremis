package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.DicUserTypeBackUp;

public interface DicUserTypeBackUpMapper {
    int insert(DicUserTypeBackUp record);

    int insertSelective(DicUserTypeBackUp record);
}
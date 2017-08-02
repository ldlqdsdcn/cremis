package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.IosPaymentInfo;

public interface IosPaymentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IosPaymentInfo record);

    int insertSelective(IosPaymentInfo record);

    IosPaymentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IosPaymentInfo record);

    int updateByPrimaryKeyWithBLOBs(IosPaymentInfo record);

    int updateByPrimaryKey(IosPaymentInfo record);
}
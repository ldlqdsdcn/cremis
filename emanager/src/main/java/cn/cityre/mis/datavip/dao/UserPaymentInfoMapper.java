package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentInfoMapper {

//    关闭服务需要
    UserPaymentInfo selectByBillCode(String billCode);

    void updateByBillCode(String billCode);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPaymentInfo record);

    int insertSelective(UserPaymentInfo record);

    UserPaymentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPaymentInfo record);

    int updateByPrimaryKey(UserPaymentInfo record);
}
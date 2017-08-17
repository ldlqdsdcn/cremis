package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPaymentInfoMapper {

//    关闭服务需要
    UserPaymentInfo selectByBillCode(String billCode);

    void updateByBillCode(String billCode);

    List<UserPaymentInfo> selectBySuid(String suid);

    UserPaymentInfo selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserPaymentInfo record);

    int updateByPrimaryKeySelective(UserPaymentInfo record);



}
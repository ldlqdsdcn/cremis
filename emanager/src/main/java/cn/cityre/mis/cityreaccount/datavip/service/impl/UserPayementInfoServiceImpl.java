package cn.cityre.mis.cityreaccount.datavip.service.impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.cityreaccount.datavip.dao.UserPaymentInfoMapper;
import cn.cityre.mis.cityreaccount.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.cityreaccount.datavip.service.UserPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/8/3.
 */
@Service(value = "userPaymentInfoService")
public class UserPayementInfoServiceImpl implements UserPaymentInfoService {
    @Autowired
    private UserPaymentInfoMapper userPaymentInfoMapper;

    /**
     * 关闭服务时页面的显示的服务时间
     *
     * @param billCode
     * @return
     */
    @Override
    public UserPaymentInfo getExistPaymentInfo(String billCode) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        UserPaymentInfo userPaymentInfo = userPaymentInfoMapper.selectByBillCode(billCode);
        DataSourceContextHolder.setDbType("dataSource_core");
        return userPaymentInfo;
    }

    /**
     * 关闭服务
     *
     * @param billCode
     */
    @Override
    public void closeServiceByBillCode(String billCode) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        userPaymentInfoMapper.updateByBillCode(billCode);
        DataSourceContextHolder.setDbType("dataSource_core");

    }

    @Override
    public List<UserPaymentInfo> getExistPaymentInfoBySuid(String suid) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        List<UserPaymentInfo> userPaymentInfos= userPaymentInfoMapper.selectBySuid(suid);
        DataSourceContextHolder.setDbType("dataSource_core");
        return userPaymentInfos;
    }

    @Override
    public void createUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        userPaymentInfoMapper.insertSelective(userPaymentInfo);
        DataSourceContextHolder.setDbType("dataSource_core");
    }


}

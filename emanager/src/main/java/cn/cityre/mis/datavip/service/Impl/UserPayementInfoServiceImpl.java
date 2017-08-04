package cn.cityre.mis.datavip.service.Impl;

import cn.cityre.mis.datavip.dao.BillsMapper;
import cn.cityre.mis.datavip.dao.UserPaymentInfoMapper;
import cn.cityre.mis.datavip.entity.UserPaymentInfo;
import cn.cityre.mis.datavip.service.BillsService;
import cn.cityre.mis.datavip.service.UserPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cityre on 2017/8/3.
 */
@Service(value = "userPaymentInfoService")
public class UserPayementInfoServiceImpl implements UserPaymentInfoService {
    @Autowired
    private UserPaymentInfoMapper userPaymentInfoMapper;

    /**
     * 关闭服务时页面的显示的服务时间
     * @param billCode
     * @return
     */
    @Override
    public UserPaymentInfo getExistPaymentInfo(String billCode) {
        return userPaymentInfoMapper.selectByBillCode(billCode);
    }

    /**
     * 关闭服务
     * @param billCode
     */
    @Override
    public void closeServiceByBillCode(String billCode) {
        userPaymentInfoMapper.updateByBillCode(billCode);
    }

    @Override
    public UserPaymentInfo getExistPaymentInfoBySuid(String suid) {
        return userPaymentInfoMapper.selectBySuid(suid);
    }

    @Override
    public void createUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
        userPaymentInfoMapper.insertSelective(userPaymentInfo);
    }


}

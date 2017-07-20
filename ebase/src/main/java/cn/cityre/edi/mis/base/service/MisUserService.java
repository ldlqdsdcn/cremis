package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisUserPo;

import java.util.Date;
import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface MisUserService {
    MisUserPo selectByUserId (String userId);
    MisUserPo selectByUid(String uid);
    List<MisUserPo> selectByRealName(String RealName);
    List<MisUserPo> selectByTime(String createStartTime,String createEndTime);
    void updateById(MisUserPo misUserPo);
}

package cn.cityre.mis.merchant.dao;


import cn.cityre.mis.merchant.entity.po.CouserInfo;
import cn.cityre.mis.merchant.entity.po.Perdaycount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface CouserInfoDao {
    List<CouserInfo> selectCouserInfoList(Map<String, Object> map);
    int selectCouserInfoCount(Map<String, Object> map);
    List<String> selectTelByUid(String uid);
    List<String> selectEmailByUid(String uid);
    int selectAdCountByUid(String uid);
    List<Perdaycount> selectPerdaycount(Map<String, Object> map);
    int updateUserlist_remark_uid(Map<String, Object> map);

    String selectDealcodeFromQd_forsale(String uid);
    int updateQd_forsale_validDate(String uid);
    String selectDealcodeFromQd_lease(String uid);
    int updateQd_lease_validDate(String uid);

    int updateCoByCocode(Map<String, Object> map);

    int selectCount_qd_forsale(String uid);

    int selectCount_qd_lease(String uid);

    int deleteFromCouser(String uid);

    int updateQd_forsale_source(String uid);

    int updateQd_lease_source(String uid);

    int updateUserlistChange(String uid);
}

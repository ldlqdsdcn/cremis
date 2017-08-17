package cn.cityre.mis.merchant.dao;


import cn.cityre.mis.merchant.entity.po.CoCheck;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface CoCheckDao {
    List<CoCheck> selectCoCheckList(Map<String, Object> map);
    int selectCoCheckCount(Map<String, Object> map);
    int updateCoCheckAll(Map<String, Object> map);
    CoCheck selectCoById(Integer id);
    int updateCoByCoCode(Map<String, Object> map);
    //经纪人转为公司用户
    int updateCouserByUsertype(String cocode);
    //经纪人转为公司用户
    int updateCouserInnerCo(String cocode);

    String selectAdInnerCo(String cocode);
    int updateAdByUidAndCocode(Map<String,Object>map);
    CoCheck selectCoByCocode(String  cocode);
    int updateCoCheckOne(Map<String, Object> map);
    int updateQd_forsale(Map<String,Object> map);
    int updateQd_lease(Map<String,Object> map);
    String selectDealcodeFromQd_forsale(String couid);
    String selectDealcodeFromQd_lease(String couid);

    int selectPro_fl_history(Map<String,Object> map);

    CoCheck selectCoByCocodeCertification(Map<String,Object> map);

    int deleteCoByCocode(String cocode);
    int deleteCo_matchinfoByCocode(String cocode);

    int updateCouidByCocode(Map<String,Object> map);
}

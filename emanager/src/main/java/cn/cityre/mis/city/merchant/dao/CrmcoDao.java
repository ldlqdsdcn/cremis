package cn.cityre.mis.city.merchant.dao;


import cn.cityre.mis.city.merchant.entity.po.Cocl;
import cn.cityre.mis.city.merchant.entity.po.Crmco;
import cn.cityre.mis.city.merchant.entity.po.Perdaycount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface CrmcoDao {
    List<Cocl> selectCocl();

    List<Crmco> selectCrmcoList(Map<String, Object> map);
    int selectCrmcoCount(Map<String, Object> map);

    int selectCouserCnt(Map<String, Object> map);

    List<Perdaycount> selectPerdaycount(Map<String, Object> map);

    int updateCoStateByCocode(Map<String, Object> map);

    int updateCo_remark_cocode(Map<String, Object> map);

    int selectCoByConameCnt(Map<String, Object> map);

    int updateCoDetail(Map<String, Object> map);

    int seleteHa_coByCocodeCnt(String cocode);
    int seleteAdByCocodeCnt(String cocode);
}

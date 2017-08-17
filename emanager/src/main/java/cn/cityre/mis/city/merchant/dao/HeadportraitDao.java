package cn.cityre.mis.city.merchant.dao;


import cn.cityre.mis.city.merchant.entity.po.Headportrait;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface HeadportraitDao {
    List<Headportrait> selectHeadportraitList_reguser(Map<String, Object> map);
    int selectHeadportraitCount_reguser(Map<String, Object> map);

    List<Headportrait> selectHeadportraitList_co(Map<String, Object> map);
    int selectHeadportraitCount_co(Map<String, Object> map);

    List<Headportrait> selectHeadportraitList_couser(Map<String, Object> map);
    int selectHeadportraitCount_couser(Map<String, Object> map);

    int updateHeadportrait_user(Map<String, Object> map);

    int updateHeadportrait_co(Map<String, Object> map);

    int updateHeadportraitAll_user(Map<String, Object> map);

    int updateHeadportraitAll_co(Map<String, Object> map);
}

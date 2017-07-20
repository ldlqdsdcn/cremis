package cn.cityre.edi.mis.base.dao;

import cn.cityre.edi.mis.base.entity.po.MisUserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
@Repository
public interface MisUserDao {
    MisUserPo selectByUserId( String userId);
    MisUserPo selectByUid(String uid);
    List<MisUserPo> selectByRealName(String realName);
    List<MisUserPo> selectByCreateTime(@Param("createStartTime") String CreateStartTime,@Param(value = "createEndTime") String CreateEndTime);
    void updateById(MisUserPo misUserPo);
}

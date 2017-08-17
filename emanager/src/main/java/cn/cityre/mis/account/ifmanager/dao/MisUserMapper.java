package cn.cityre.mis.account.ifmanager.dao;

import cn.cityre.mis.account.ifmanager.entity.MisUserPo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisUserMapper {
    PageMyBatis<MisUserPo> selectByPage(Map<String, Object> map);

    List<MisUserPo> selectUserOrderByCreateTime();

    MisUserPo selectByUserId(String userId);

    MisUserPo selectByUid(String uid);

    List<MisUserPo> selectByRealName(String realName);

    List<MisUserPo> selectByCreateTime(@Param("createStartTime") String CreateStartTime, @Param(value = "createEndTime") String CreateEndTime);

    void updateById(MisUserPo misUserPo);

    MisUserPo selectById(Integer id);
}

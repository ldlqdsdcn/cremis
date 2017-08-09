package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisUserPo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisUserMapper {
    PageMyBatis<MisUserPo> selectByPage(PagingCriteria pagingCriteria);

    MisUserPo selectByUserId(String userId);

    MisUserPo selectByUid(String uid);

    List<MisUserPo> selectByRealName(String realName);

    List<MisUserPo> selectByCreateTime(@Param("createStartTime") String CreateStartTime, @Param(value = "createEndTime") String CreateEndTime);

    void updateById(MisUserPo misUserPo);

    MisUserPo selectById(Integer id);
}

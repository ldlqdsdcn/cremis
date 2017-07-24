package cn.cityre.mis.account.dao;

import cn.cityre.mis.account.entity.po.MisUserEmailPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
@Repository
public interface MisEmailDao {
    MisUserEmailPo selectByUid(@Param(value = "unionUid") String unionUid, @Param(value = "isVerified") Byte isVerified,
                               @Param(value = "isPrimary") Byte isPrimary);

    void updateByUid(MisUserEmailPo misUserEmailPo);

    List<MisUserEmailPo> selectEmailList();

    MisUserEmailPo selectById(Integer id);

    void createEmail(MisUserEmailPo misUserEmailPo);

    void updateById(MisUserEmailPo misUserEmailPo);

    void deleteById(Integer id);
}

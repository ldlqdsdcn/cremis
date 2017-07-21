package cn.cityre.mis.account.dao;

import cn.cityre.mis.account.entity.po.MisUserPhonePo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
@Repository
public interface MisPhoneDao {
//    List<MisUserPhonePo> selectByUid(@Param(value = "unionUid")String unionUid,@Param(value = "isVerified") Byte isVerified,
//                               @Param(value = "isPrimary") Byte isPrimary);

    List<MisUserPhonePo> selectByUid(@Param(value = "unionUid") String unionUid);

    MisUserPhonePo selectById(Integer id);

    void updateByUid(MisUserPhonePo misUserPhonePo);

    List<MisUserPhonePo> selectPhoneList();

    void createPhone(MisUserPhonePo misUserPhonePo);

    void updateById(MisUserPhonePo misUserPhonePo);

    void deleteById(Integer id);
}

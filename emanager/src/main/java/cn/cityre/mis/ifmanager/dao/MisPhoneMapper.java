package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisUserPhonePo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisPhoneMapper {

    List<MisUserPhonePo> selectByUid(@Param(value = "unionUid") String unionUid);

    MisUserPhonePo selectById(Integer id);

    void updateByUid(MisUserPhonePo misUserPhonePo);

    List<MisUserPhonePo> selectPhoneList();

    void createPhone(MisUserPhonePo misUserPhonePo);

    void updateById(MisUserPhonePo misUserPhonePo);

    void deleteById(Integer id);
}

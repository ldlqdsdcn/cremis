package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisPhonePinPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisPhonePinMapper {

    List<MisPhonePinPo> selectByPhone(String phone);

    List<MisPhonePinPo> selectAllList();

    MisPhonePinPo selectById(Integer id);

    void createPhonePin(MisPhonePinPo misPhonePinPo);

    void updateById(MisPhonePinPo misPhonePinPo);

    void  deleteById(Integer id);
}

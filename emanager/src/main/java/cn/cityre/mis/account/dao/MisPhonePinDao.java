package cn.cityre.mis.account.dao;

import cn.cityre.mis.account.entity.po.MisPhonePinPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
@Repository
public interface MisPhonePinDao {
    List<MisPhonePinPo> selectByPhone(String phone);

    List<MisPhonePinPo> selectAllList();

    MisPhonePinPo selectById(Integer id);

    void createPhonePin(MisPhonePinPo misPhonePinPo);

    void updateById(MisPhonePinPo misPhonePinPo);

    void  deleteById(Integer id);
}

package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisPhonePinPo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisPhonePinMapper {
   PageMyBatis<MisPhonePinPo> selectByPage(Map<String,Object> map);

    Integer selectCount(Map<String,Object>map);

    List<MisPhonePinPo> selectByPhone(String phone);

    List<MisPhonePinPo> selectAllList();

    MisPhonePinPo selectById(Integer id);

    void createPhonePin(MisPhonePinPo misPhonePinPo);

    void updateById(MisPhonePinPo misPhonePinPo);

    void  deleteById(Integer id);
}

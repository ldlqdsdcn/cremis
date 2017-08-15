package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisUserPhonePo;
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
public interface MisPhoneMapper {

    PageMyBatis<MisUserPhonePo> selectByPage(Map<String,Object>map);

    List<MisUserPhonePo> selectByUid(@Param(value = "unionUid") String unionUid);

    MisUserPhonePo selectById(Integer id);

    void updateByUid(MisUserPhonePo misUserPhonePo);

    List<MisUserPhonePo> selectPhoneList();

    void createPhone(MisUserPhonePo misUserPhonePo);

    void updateById(MisUserPhonePo misUserPhonePo);

    void deleteById(Integer id);
}

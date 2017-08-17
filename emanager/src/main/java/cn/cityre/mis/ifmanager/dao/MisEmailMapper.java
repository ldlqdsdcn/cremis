package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisUserEmailPo;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisEmailMapper {
    PageMyBatis<MisUserEmailPo> selectByPage(Map<String,Object>map);

    void updateByUid(MisUserEmailPo misUserEmailPo);

    List<MisUserEmailPo> selectEmailList();

    MisUserEmailPo selectById(Integer id);

    void createEmail(MisUserEmailPo misUserEmailPo);

    void updateById(MisUserEmailPo misUserEmailPo);

    void deleteById(Integer id);

}

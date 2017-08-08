package cn.cityre.mis.ifmanager.dao;

import cn.cityre.mis.ifmanager.entity.MisUserEmailPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisEmailMapper {

    void updateByUid(MisUserEmailPo misUserEmailPo);

    List<MisUserEmailPo> selectEmailList();

    MisUserEmailPo selectById(Integer id);

    void createEmail(MisUserEmailPo misUserEmailPo);

    void updateById(MisUserEmailPo misUserEmailPo);

    void deleteById(Integer id);

}

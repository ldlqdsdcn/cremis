package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.DicPayType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/16.
 */
@Repository
public interface DicPayTypeMapper {
    List<DicPayType> selectAllType();
}

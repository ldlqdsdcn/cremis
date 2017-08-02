package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.AppProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppProductMapper {
    List<AppProduct> selectAllList();

    int deleteByPrimaryKey(Integer id);

    int insert(AppProduct appProduct);

    AppProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AppProduct appProduct);

}
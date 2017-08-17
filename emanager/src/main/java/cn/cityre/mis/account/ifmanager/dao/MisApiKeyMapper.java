package cn.cityre.mis.account.ifmanager.dao;

import cn.cityre.mis.account.ifmanager.entity.MisApiKeyPo;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
@Repository
public interface MisApiKeyMapper {
    PageMyBatis<MisApiKeyPo> selectByPage(PagingCriteria pagingCriteria);

    MisApiKeyPo selectByKey(String apiKey);

    List<MisApiKeyPo> selectAllApiKey();

    void updateByKey(MisApiKeyPo misApiKeyPo);

    void deleteByKey(String apiKey);

    void createApiKey(MisApiKeyPo misApiKeyPo);

    void logicDeleteById(Integer id);
}

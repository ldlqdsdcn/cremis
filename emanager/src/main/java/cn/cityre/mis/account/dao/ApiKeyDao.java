package cn.cityre.mis.account.dao;

import cn.cityre.mis.account.entity.po.MisApiKeyPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface ApiKeyDao {
    MisApiKeyPo selectByKey(String apiKey);

    List<MisApiKeyPo> selectAllApiKey();

    void updateByKey(MisApiKeyPo misApiKeyPo);

    void deleteByKey(String apiKey);

    void createApiKey(MisApiKeyPo misApiKeyPo);

    void logicDeleteById(Integer id);
}

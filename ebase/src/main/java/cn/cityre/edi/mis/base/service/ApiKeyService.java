package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisApiKeyPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/10.
 */
public interface ApiKeyService {
//    v2017_api_key：增改查删（逻辑删）
    PaginationResult<MisApiKeyPo> getApiKeyList(Search search, QueryParams queryParams);

    void createApiKey(MisApiKeyPo misApiKeyPo);

    void updateApikey(MisApiKeyPo misApiKeyPo);
    //删除
    void logicDeleteApiKey(Integer[] ids);

    MisApiKeyPo getApiKey(String  apiKey);

//    逻辑删除
    void deleteById(Integer[] ids);
}

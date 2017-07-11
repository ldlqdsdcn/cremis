package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.ApiKeyPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/10.
 */
public interface ApiKeyService {
//    v2017_api_key：增改查删（逻辑删）
    PaginationResult<ApiKeyPo> getApiKeyList(Search search, QueryParams queryParams);

    void saveApiKey(ApiKeyPo apiKeyPo);
    //逻辑删
    void deleteApiKey(Integer[] ids);

    ApiKeyPo getApiKey(Integer id);
}

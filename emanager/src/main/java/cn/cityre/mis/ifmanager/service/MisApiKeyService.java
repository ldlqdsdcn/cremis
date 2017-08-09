package cn.cityre.mis.ifmanager.service;

import cn.cityre.mis.ifmanager.entity.MisApiKeyPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
public interface MisApiKeyService {

//        v2017_api_key：增改查删（逻辑删）
    PaginationResult<MisApiKeyPo> getApiKeyList(Search search, QueryParams queryParams);

//        PaginationResult<MisApiKeyPo> getApiKeyList(List<SearchField> searchFields, QueryParams queryParams);


    void createApiKey(MisApiKeyPo misApiKeyPo);

    void updateApikey(MisApiKeyPo misApiKeyPo);

    //删除
    void logicDeleteApiKey(Integer[] ids);

    MisApiKeyPo getApiKey(String apiKey);

    //    逻辑删除
    void deleteById(Integer[] ids);
}

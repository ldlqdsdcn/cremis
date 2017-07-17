package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisUserEmailPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/12.
 */
public interface MisUserEmailService {
    PaginationResult<MisUserEmailPo> getUserEmailList(Search search, QueryParams queryParams);
    MisUserEmailPo getUserEmail(Integer id);
    void saveUserEmail(MisUserEmailPo misUserEmailPo);
    void deleteUserEmail(Integer[] ids);
}

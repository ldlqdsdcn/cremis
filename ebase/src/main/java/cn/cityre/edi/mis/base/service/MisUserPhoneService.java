package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisUserPhonePo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/12.
 */
public interface MisUserPhoneService {
    PaginationResult<MisUserPhonePo> getUserPhoneList(Search search, QueryParams queryParams);
    MisUserPhonePo getUserPhone(Integer id);
    void saveUserPhone(MisUserPhonePo misUserPhonePo);
    void deleteUserPhone(Integer[] ids);
}

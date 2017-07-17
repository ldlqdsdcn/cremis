package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisPhonePinPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/12.
 */
public interface MisPhonePinService {
    PaginationResult<MisPhonePinPo> getMisPhonePinList(Search search, QueryParams queryParams);
    MisPhonePinPo getPhonePin(Integer id);
    void savePhonePin(MisPhonePinPo misPhonePinPo);
    void deletePhonePin(Integer[] ids);
}

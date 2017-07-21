package cn.cityre.mis.account.service;

import cn.cityre.mis.account.entity.po.MisPhonePinPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface PhonePinService {
    PaginationResult<MisPhonePinPo> getMisPhonePinList(Search search, QueryParams queryParams);

    List<MisPhonePinPo> getExistPhonePinByPhone(String phone);

    List<MisPhonePinPo> getExistPhonePinList();

    MisPhonePinPo getPhonePinById(Integer id);

    void createPhonePin(MisPhonePinPo misPhonePinPo);

    void updatePhonePin(MisPhonePinPo misPhonePinPo);

    void deleteById(Integer[] ids);
}

package cn.cityre.mis.account.service;

import cn.cityre.mis.account.entity.po.MisUserPhonePo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface MisPhoneService {

    PaginationResult<MisUserPhonePo> getUserPhoneList(Search search, QueryParams queryParams);

    List<MisUserPhonePo> getExistPhoneByUid(String unionUid);

    MisUserPhonePo getExistPhoneById(Integer id);

    void updatePhoneByUid(MisUserPhonePo misUserPhonePo);

    List<MisUserPhonePo> getExistPhoneList();

    void createPhone(MisUserPhonePo misUserPhonePo);

    void updateById(MisUserPhonePo misUserPhonePo);

    void deleteById(Integer[] ids);

    boolean getExistPrimaryPhoneByUid(MisUserPhonePo misUserPhonePo);

}

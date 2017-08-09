package cn.cityre.mis.ifmanager.service;

import cn.cityre.mis.ifmanager.entity.MisUserPhonePo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface MisPhoneService {
    PaginationResult<MisUserPhonePo> getUserPhoneListByMyBatis(List<SearchField> searchFields, QueryParams queryParams);

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

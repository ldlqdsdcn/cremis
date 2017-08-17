package cn.cityre.mis.ifmanager.service;

import cn.cityre.mis.ifmanager.entity.MisPhonePinPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;

/**
 * Created by cityre on 2017/8/8.
 */
public interface MisPhonePinService {

    PaginationResult<MisPhonePinPo> getMisPhonePinListMybatis(List<SearchField> search, QueryParams queryParams);

    PaginationResult<MisPhonePinPo> getMisPhonePinList(Search search, QueryParams queryParams);

    List<MisPhonePinPo> getExistPhonePinByPhone(String phone);

    List<MisPhonePinPo> getExistPhonePinList();

    MisPhonePinPo getPhonePinById(Integer id);

    void createPhonePin(MisPhonePinPo misPhonePinPo);

    void updatePhonePin(MisPhonePinPo misPhonePinPo);

    void deleteById(Integer[] ids);
}

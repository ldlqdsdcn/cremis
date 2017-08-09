package cn.cityre.mis.ifmanager.service;

import cn.cityre.mis.ifmanager.entity.MisUserEmailPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface MisEmailService {
    PaginationResult<MisUserEmailPo> getUserEmailListByMybatis(List<SearchField>searchFields,QueryParams queryParams);

    PaginationResult<MisUserEmailPo> getUserEmailList(Search search, QueryParams queryParams);

//    MisUserEmailPo getExistEmailByUid(String unionUid, Byte isVerified, Byte isPrimary);

    void updateEmailByUid(MisUserEmailPo misUserEmailPo);

    List<MisUserEmailPo> getEmaliList();

    void createEmail(MisUserEmailPo misUserEmailPo);

    void updateEmailById(MisUserEmailPo misUserEmailPo);

    void deleteEmailById(Integer[] ids);
    
    MisUserEmailPo getExistUserEmailById(Integer id);
}

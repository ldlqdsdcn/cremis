package cn.cityre.mis.ifmanager.service;

import cn.cityre.mis.ifmanager.entity.MisUserPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchFacade;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface MisUserService {
    PaginationResult<MisUserPo> getExistUserList(Search search, QueryParams queryParams);

    PaginationResult<MisUserPo> getExistUserListByMybatis(List<SearchField> searchFields, QueryParams queryParams);

    MisUserPo selectByUserId(String userId);

    MisUserPo selectByUid(String uid);

    List<MisUserPo> selectByRealName(String RealName);

    List<MisUserPo> selectByTime(String createStartTime, String createEndTime);

    void updateById(MisUserPo misUserPo);

    MisUserPo getExistUserById(Integer id);
}

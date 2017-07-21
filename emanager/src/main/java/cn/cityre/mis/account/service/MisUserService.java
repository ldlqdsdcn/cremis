package cn.cityre.mis.account.service;

import cn.cityre.mis.account.entity.po.MisUserPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

import java.util.List;

/**
 * Created by cityre on 2017/7/19.
 */
public interface MisUserService {
    PaginationResult<MisUserPo> getExistUserList(Search search, QueryParams queryParams);
    MisUserPo selectByUserId (String userId);
    MisUserPo selectByUid(String uid);
    List<MisUserPo> selectByRealName(String RealName);
    List<MisUserPo> selectByTime(String createStartTime,String createEndTime);
    void updateById(MisUserPo misUserPo);
    MisUserPo getExistUserById(Integer id);
}

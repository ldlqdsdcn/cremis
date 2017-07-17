package cn.cityre.edi.mis.base.service;

import cn.cityre.edi.mis.base.entity.po.MisUserPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by cityre on 2017/7/11.
 */
public interface UserService {
    PaginationResult<MisUserPo> getExistUserList(Search search, QueryParams queryParams);

    MisUserPo getUser(Integer id);

    void deleteUser(Integer[] ids);

    void saveUser(MisUserPo misUserPo);
}

package cn.cityre.edi.mis.base.service.impl;

import cn.cityre.edi.mis.base.entity.po.MisUserPo;
import cn.cityre.edi.mis.base.service.UserService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cityre on 2017/7/11.
 */
@Service(value = "v2017userService")
//默认是首字母小写自动命名，value定义后按照value的值命名
public class UserServiceImpl implements UserService{
    @DataAccess(entity = MisUserPo.class)
    private CommonDao<MisUserPo,Integer> userDao;
    @Override
    public PaginationResult<MisUserPo> getExistUserList(Search search, QueryParams queryParams) {
        PaginationResult<MisUserPo> poPaginationResult = null;
        search.setMaxResults(queryParams.getPageSize());
        search.setFirstResult(queryParams.getFirstResult());

        if (queryParams.isInit()){
            SearchResult searchResult = userDao.searchAndCount(search);
            poPaginationResult = PaginationResult.pagination(searchResult.getResult(),searchResult.getTotalCount(),queryParams.getPageNo(),queryParams.getPageSize());
        }else{
            List<MisUserPo> misUserPoList = userDao.search(search);
            poPaginationResult = PaginationResult.pagination(misUserPoList,queryParams.getTotalRecords(),queryParams.getPageNo(),queryParams.getPageSize());
        }
        return poPaginationResult;
    }

    @Override
    public MisUserPo getUser(Integer id) {
        MisUserPo misUserPo = userDao.find(id);
        return misUserPo;
    }

    @Override
    public void deleteUser(Integer[] ids) {
        MisUserPo[] misUserPos = userDao.find(ids);
        for (int i = 0; i<= misUserPos.length; i++){
            misUserPos[i].setIsValid((byte) 0);
        }
    }

    @Override
    public void saveUser(MisUserPo misUserPo) {
        userDao.save(misUserPo);
    }
}

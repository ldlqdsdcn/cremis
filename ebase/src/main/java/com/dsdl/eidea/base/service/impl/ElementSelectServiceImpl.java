package com.dsdl.eidea.base.service.impl;

import com.dsdl.eidea.base.entity.po.ElementSelectPo;
import com.dsdl.eidea.base.service.ElementSelectService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 车东明 on 2017/5/27.
 */
@Service(value = "ElementSelectService")
public class ElementSelectServiceImpl implements ElementSelectService{
    @DataAccess(entity = ElementSelectPo.class)
    private CommonDao<ElementSelectPo,Integer> elementSelectDao;
    @Override
    public PaginationResult<ElementSelectPo> getElementSelectListByPaging(Search search, ItemParams itemParams) {
       if (itemParams.getQueryParams()==null){
           itemParams.setQueryParams(new QueryParams());
       }
       QueryParams queryParams = itemParams.getQueryParams();
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        search.addFilterEqual("elementId",itemParams.getItemPK());
        PaginationResult<ElementSelectPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<ElementSelectPo> searchResult = elementSelectDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<ElementSelectPo> datadictPoList = elementSelectDao.search(search);
            paginationResult = PaginationResult.pagination(datadictPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;

    }
//@Override
//public PaginationResult<ElementSelectPo> getElementSelectListByPaging(Search search, QueryParams queryParams,Integer id) {
//    search.setFirstResult(queryParams.getFirstResult());
//    search.setMaxResults(queryParams.getPageSize());
//    search.addFilterEqual("elementId",id);
//    PaginationResult<ElementSelectPo> paginationResult = null;
//    if (queryParams.isInit()) {
//        SearchResult<ElementSelectPo> searchResult = elementSelectDao.searchAndCount(search);
//        paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
//    } else {
//        List<ElementSelectPo> datadictPoList = elementSelectDao.search(search);
//        paginationResult = PaginationResult.pagination(datadictPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
//    }
//    return paginationResult;
//
//}

    @Override
    public List<ElementSelectPo> getList(Integer id) {
        Search search = new Search();
        search.addFilterEqual("elementId",id);
        return elementSelectDao.search(search);
    }

    @Override
    public ElementSelectPo getElementSelect(Integer id) {
        return elementSelectDao.find(id);
    }

    @Override
    public void saveElementSelect(ElementSelectPo elementPo) {
        elementSelectDao.save(elementPo);
    }

    @Override
    public void deletes(Integer[] ids) {
        elementSelectDao.removeByIds(ids);
    }
}

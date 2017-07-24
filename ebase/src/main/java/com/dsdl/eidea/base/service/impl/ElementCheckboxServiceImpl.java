package com.dsdl.eidea.base.service.impl;

import com.dsdl.eidea.base.entity.po.ElementCheckboxPo;
import com.dsdl.eidea.base.service.ElementCheckboxService;
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
 * Created by 车东明 on 2017/6/1.
 */
@Service
public class ElementCheckboxServiceImpl implements ElementCheckboxService {
    @DataAccess( entity = ElementCheckboxPo.class)
    CommonDao<ElementCheckboxPo,Integer> elementCheckboxDao;
//    @Override
//    public PaginationResult<ElementCheckboxPo> getElementCheckboxListByPaging(Search search, QueryParams queryParams) {
//        search.setFirstResult(queryParams.getFirstResult());
//        search.setMaxResults(queryParams.getPageSize());
//        PaginationResult<ElementCheckboxPo> paginationResult = null;
//        if (queryParams.isInit()) {
//            SearchResult<ElementCheckboxPo> searchResult = elementCheckboxDao.searchAndCount(search);
//            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
//        } else {
//            List<ElementCheckboxPo> datadictPoList = elementCheckboxDao.search(search);
//            paginationResult = PaginationResult.pagination(datadictPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
//        }
//        return paginationResult;
//    }
@Override
public PaginationResult<ElementCheckboxPo> getElementCheckboxListByPaging(Search search, ItemParams<Integer> itemParams) {
    QueryParams queryParams = itemParams.getQueryParams();
    search.setFirstResult(queryParams.getFirstResult());
    search.setMaxResults(queryParams.getPageSize());
    search.addFilterEqual("elementId",itemParams.getItemPK());
    PaginationResult<ElementCheckboxPo> paginationResult = null;
    if (queryParams.isInit()) {
        SearchResult<ElementCheckboxPo> searchResult = elementCheckboxDao.searchAndCount(search);
        paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
    } else {
        List<ElementCheckboxPo> datadictPoList = elementCheckboxDao.search(search);
        paginationResult = PaginationResult.pagination(datadictPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
    }
    return paginationResult;
}

    @Override
    public ElementCheckboxPo getElementCheckbox(Integer id) {

        return elementCheckboxDao.find(id);
    }

    @Override
    public void saveElementCheckbox(ElementCheckboxPo elementCheckboxPo) {
        elementCheckboxDao.save(elementCheckboxPo);
    }

    @Override
    public void deletes(Integer[] ids) {
        elementCheckboxDao.removeByIds(ids);
    }
}

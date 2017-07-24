package com.dsdl.eidea.base.service.impl;

import com.dsdl.eidea.base.entity.po.ElementLinkedPo;
import com.dsdl.eidea.base.service.ElementLinkedService;
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
public class ElementLinkedServiceImpl implements ElementLinkedService {
    @DataAccess(entity = ElementLinkedPo.class)
    CommonDao<ElementLinkedPo,Integer> elementLinkedDao;
    @Override
    public PaginationResult<ElementLinkedPo> getElementLinkedList(Search search, ItemParams<Integer> itemParams) {
        QueryParams queryParams = itemParams.getQueryParams();
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        search.addFilterEqual("elementId",itemParams.getItemPK());
        PaginationResult<ElementLinkedPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<ElementLinkedPo> searchResult = elementLinkedDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<ElementLinkedPo> elementLinkedPoList = elementLinkedDao.search(search);
            paginationResult = PaginationResult.pagination(elementLinkedPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;
    }

    @Override
    public ElementLinkedPo getElementLinkedPo(Integer id) {
        return elementLinkedDao.find(id);
    }

    @Override
    public void saveElementLinkedPo(ElementLinkedPo elementLinkedPo) {
        elementLinkedDao.save(elementLinkedPo);
    }

    @Override
    public void deleteElementLinkedPo(Integer[] ids) {
        elementLinkedDao.removeByIds(ids);
    }
}

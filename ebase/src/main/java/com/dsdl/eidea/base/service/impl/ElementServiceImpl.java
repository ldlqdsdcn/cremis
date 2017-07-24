package com.dsdl.eidea.base.service.impl;

import com.dsdl.eidea.base.entity.po.ElementPo;
import com.dsdl.eidea.base.service.ElementService;
import com.dsdl.eidea.core.dao.CommonDao;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.dsdl.eidea.core.spring.annotation.DataAccess;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 车东明 on 2017/5/27.
 */
@Service("ElementService")
public class ElementServiceImpl implements ElementService{
    @DataAccess(entity = ElementPo.class)
    CommonDao<ElementPo,Integer> elementDao;
    @Override
    public PaginationResult<ElementPo> getElementListByPaging(Search search, QueryParams queryParams) {
        search.setFirstResult(queryParams.getFirstResult());
        search.setMaxResults(queryParams.getPageSize());
        PaginationResult<ElementPo> paginationResult = null;
        if (queryParams.isInit()) {
            SearchResult<ElementPo> searchResult = elementDao.searchAndCount(search);
            paginationResult = PaginationResult.pagination(searchResult.getResult(), searchResult.getTotalCount(), queryParams.getPageNo(), queryParams.getPageSize());
        } else {
            List<ElementPo> datadictPoList = elementDao.search(search);
            paginationResult = PaginationResult.pagination(datadictPoList, queryParams.getTotalRecords(), queryParams.getPageNo(), queryParams.getPageSize());
        }
        return paginationResult;

    }

    @Override
    public ElementPo getElement(Integer id) {
        return elementDao.find(id);
    }

    @Override
    public void saveElement(ElementPo elementPo) {
        elementDao.save(elementPo);
    }

    @Override
    public void deletes(Integer[] ids) {
        elementDao.removeByIds(ids);
    }
}

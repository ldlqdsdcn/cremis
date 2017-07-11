package com.dsdl.eidea.base.service;

import com.dsdl.eidea.base.entity.po.ElementPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by 车东明 on 2017/5/27.
 */
public interface ElementService {
    PaginationResult<ElementPo> getElementListByPaging(Search search, QueryParams queryParams);
    ElementPo getElement(Integer id);
    void saveElement(ElementPo elementPo);
    void deletes(Integer[] ids);
}

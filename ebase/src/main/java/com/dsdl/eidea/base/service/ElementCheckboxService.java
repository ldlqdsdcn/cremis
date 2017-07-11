package com.dsdl.eidea.base.service;

import com.dsdl.eidea.base.entity.po.ElementCheckboxPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by 车东明 on 2017/6/1.
 */
public interface ElementCheckboxService {
//    PaginationResult<ElementCheckboxPo> getElementCheckboxListByPaging(Search search, QueryParams queryParams);
    PaginationResult<ElementCheckboxPo> getElementCheckboxListByPaging(Search search, ItemParams<Integer> itemParams);
    ElementCheckboxPo getElementCheckbox(Integer id);
    void saveElementCheckbox(ElementCheckboxPo elementCheckboxPo);
    void deletes(Integer[] ids);
}

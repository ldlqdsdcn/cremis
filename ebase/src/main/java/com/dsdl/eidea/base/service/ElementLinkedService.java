package com.dsdl.eidea.base.service;

import com.dsdl.eidea.base.entity.po.ElementLinkedPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by 车东明 on 2017/6/1.
 */
public interface ElementLinkedService {
    PaginationResult<ElementLinkedPo> getElementLinkedList(Search search, ItemParams<Integer> itemParams);
    ElementLinkedPo getElementLinkedPo(Integer id);
    void saveElementLinkedPo(ElementLinkedPo elementLinkedPo);
    void deleteElementLinkedPo(Integer[] ids);
}

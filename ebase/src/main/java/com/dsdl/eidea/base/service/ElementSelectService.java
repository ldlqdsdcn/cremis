package com.dsdl.eidea.base.service;

import com.dsdl.eidea.base.entity.po.ElementSelectPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.ItemParams;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

import java.util.List;

/**
 * Created by 车东明 on 2017/5/27.
 */
public interface ElementSelectService {
    PaginationResult<ElementSelectPo> getElementSelectListByPaging(Search search, ItemParams<Integer> itemParams);
//    PaginationResult<ElementSelectPo> getElementSelectListByPaging(Search search, QueryParams queryParams,Integer id);
    ElementSelectPo getElementSelect(Integer id);
    List<ElementSelectPo> getList(Integer id);
    void saveElementSelect(ElementSelectPo elementPo);
    void deletes(Integer[] ids);
}

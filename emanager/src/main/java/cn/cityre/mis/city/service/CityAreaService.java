package cn.cityre.mis.city.service;

import cn.cityre.edi.mis.base.entity.po.AreaPo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;

/**
 * Created by 刘大磊 on 2017/7/6 9:25.
 */
public interface CityAreaService {

    PaginationResult<AreaPo> getAreaListByPaging(Search search, QueryParams queryParams);

    AreaPo getArea(Integer id);

    void saveArea(AreaPo area);

    void deletes(Integer[] ids);
}

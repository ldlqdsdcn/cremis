package cn.cityre.mis.city.service.impl;

import cn.cityre.edi.mis.base.entity.po.AreaPo;
import cn.cityre.mis.city.service.CityAreaService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.Search;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by 刘大磊 on 2017/7/6 9:37.
 */
@Service
@Scope("prototype")
public class CityAreaServiceImpl implements CityAreaService {

    @Override
    public PaginationResult<AreaPo> getAreaListByPaging(Search search, QueryParams queryParams) {
        return null;
    }

    @Override
    public AreaPo getArea(Integer id) {
        return null;
    }

    @Override
    public void saveArea(AreaPo area) {

    }

    @Override
    public void deletes(Integer[] ids) {

    }
}

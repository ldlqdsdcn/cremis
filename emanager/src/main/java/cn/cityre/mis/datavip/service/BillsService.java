package cn.cityre.mis.datavip.service;

import cn.cityre.mis.datavip.entity.Bills;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;

import java.util.List;

/**
 * Created by cityre on 2017/7/31.
 */
public interface BillsService {

    PaginationResult<Bills> getBillsListByOthers(QueryParams queryParams);

    Bills getExistBillsById(Integer id);
}

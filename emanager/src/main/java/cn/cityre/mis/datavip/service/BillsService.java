package cn.cityre.mis.datavip.service;

import cn.cityre.mis.datavip.entity.Bills;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import org.mybatis.pagination.dto.datatables.SearchField;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by cityre on 2017/7/31.
 */
public interface BillsService {

    PaginationResult<Bills> getBillsListByOthers(QueryParams queryParams);

    void addInvoice(Bills bills);

    Bills getExistBillsById(Integer id);

    void openService(Bills bills) throws ParseException;

    PaginationResult<Bills> getUserInfoList(QueryParams queryParams);
}

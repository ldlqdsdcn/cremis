package cn.cityre.mis.datavip.service;

import cn.cityre.mis.datavip.dto.SearchBillParams;
import cn.cityre.mis.datavip.dto.SearchParams;
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

    PaginationResult<Bills> getBillsListByOthers(SearchBillParams searchBillsParams) throws ParseException;

    List<Bills> getExportList();

    List<Bills> getExistList();

    void addInvoice(Bills bills);

    Bills getExistBillsById(Integer id);

    Bills getExistBillsByCode(String billCode) throws ParseException;

    void openService(Bills bills) throws ParseException;

//    PaginationResult<Bills> getUserInfoList(List<SearchField> searchFields,QueryParams queryParams);
}

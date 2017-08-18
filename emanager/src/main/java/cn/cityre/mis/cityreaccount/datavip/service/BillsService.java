package cn.cityre.mis.cityreaccount.datavip.service;

import cn.cityre.mis.cityreaccount.datavip.dto.SearchBillParams;
import cn.cityre.mis.cityreaccount.datavip.entity.Bills;
import com.dsdl.eidea.core.dto.PaginationResult;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/31.
 */
public interface BillsService {

    PaginationResult<Bills> getBillsListByOthers(SearchBillParams searchBillsParams) throws ParseException;

    List<Bills> getExportList(Map<String,Object> map) throws ParseException;

    List<Bills> getExistList();

    void addInvoice(Bills bills);

    Bills getExistBillsById(Integer id);

    Bills getExistBillsByCode(String billCode) throws ParseException;

    void openService(Bills bills) throws ParseException;

//    PaginationResult<Bills> getUserInfoList(List<SearchField> searchFields,QueryParams queryParams);
}

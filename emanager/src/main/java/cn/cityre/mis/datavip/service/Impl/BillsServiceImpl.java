package cn.cityre.mis.datavip.service.Impl;

import cn.cityre.edi.mis.base.util.DataSourceContextHolder;
import cn.cityre.mis.datavip.dao.BillsMapper;
import cn.cityre.mis.datavip.entity.Bills;
import cn.cityre.mis.datavip.service.BillsService;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.mybatis.pagination.dto.datatables.SearchField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by cityre on 2017/7/31.
 */
@Service
public class BillsServiceImpl implements BillsService {
    @Autowired
    private BillsMapper billsMapper;
    @Override
    public PaginationResult<Bills> getBillsListByOthers(QueryParams queryParams) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        PagingCriteria pagingCriteria = PagingCriteria.createCriteria(queryParams.getPageSize(),queryParams.getFirstResult(),queryParams.getPageNo());
        PageMyBatis<Bills> pageMyBatis = billsMapper.selectDefaultByPage(pagingCriteria);
        PaginationResult<Bills> paginationResult = PaginationResult.pagination(pageMyBatis,(int)pageMyBatis.getTotal(),pagingCriteria.getPageNumber(),pagingCriteria.getDisplaySize());
        return paginationResult;
    }

    @Override
    public void addInvoice(Bills bills) {
        DataSourceContextHolder.setDbType("dataSource_cityreaccount");
        billsMapper.updateInvoice(bills);
    }

    @Override
    public Bills getExistBillsById(Integer id) {
        return billsMapper.selectByPrimaryKey(id);
    }
}

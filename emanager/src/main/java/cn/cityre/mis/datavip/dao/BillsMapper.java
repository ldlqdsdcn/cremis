package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.Bills;
import org.apache.ibatis.annotations.Param;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface BillsMapper {
    //asp中default查询方法
    PageMyBatis<Bills> selectDefaultByPage(PagingCriteria pagingCriteria);
//    登记发票
    void updateInvoice(Bills bills);

    int deleteByPrimaryKey(Integer id);

    int insert(Bills record);

    int insertSelective(Bills record);

    Bills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bills record);

    int updateByPrimaryKey(Bills record);
}
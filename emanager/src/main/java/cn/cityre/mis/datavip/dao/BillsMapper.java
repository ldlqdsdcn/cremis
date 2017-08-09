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
    //数据会员查询的方法,new/service.asp
//    PageMyBatis<Bills> selectUserInfoByPage(PagingCriteria pagingCriteria);
//    登记发票
    void updateInvoice(Bills bills);

    List<Bills> selectByBigBillCode(String bigBillCode);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Bills record);

    Bills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bills record);

    void updateByBillsId(Bills bills);
}
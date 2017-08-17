package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.Bills;
import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface BillsMapper {
    //asp中default查询方法
    PageMyBatis<Bills> selectDefaultByPage(Map<String, Object> map);

    List<Bills> selectExportInfo();

    Bills selectByCode(String billCode);

    Date selectTime();

    List<Bills> selectAllBills();

    void updateInvoice(Bills bills);

    List<Bills> selectByBigBillCode(String bigBillCode);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Bills record);

    Bills selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bills record);

    void updateByBillsId(Bills bills);
}
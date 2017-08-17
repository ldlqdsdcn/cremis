package cn.cityre.mis.cityreaccount.datavip.dao;

import cn.cityre.mis.cityreaccount.datavip.entity.BillsBackup;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsBackupMapper {
    int insert(BillsBackup record);

    int insertSelective(BillsBackup record);
}
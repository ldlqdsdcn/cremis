package cn.cityre.mis.datavip.dao;

import cn.cityre.mis.datavip.entity.BillsBackup;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsBackupMapper {
    int insert(BillsBackup record);

    int insertSelective(BillsBackup record);
}
package cn.cityre.mis.city.tradeProcess.service;


import cn.cityre.mis.city.tradeProcess.entity.po.Qd_contract;
import cn.cityre.mis.city.tradeProcess.entity.po.Qd_contract_history;
import com.dsdl.eidea.util.Page;

import java.util.List;


public interface UserApplyCheckHouseService {
	
	public List<Qd_contract> userApplyCheckHouseList(String startDate, String endDate, String phone, String status, Page pager);
	public void addBankInfo(Integer id, String bankname, String amount, String date, Integer status, String preBankName, String preAmount, String preDate, String preMisName, String sysuid);
	public void addHouseInfo(Integer id, String district, String street, String stno, String ha, String bldgno, String unit, String roomno);
	
	public List<Qd_contract_history> historyList(Integer id, Page pager);
}

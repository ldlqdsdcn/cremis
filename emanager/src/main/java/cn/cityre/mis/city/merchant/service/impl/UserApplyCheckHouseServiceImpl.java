package cn.cityre.mis.city.merchant.service.impl;


import cn.cityre.mis.city.merchant.dao.Qd_contractDao;
import cn.cityre.mis.city.merchant.dao.UserListDao;
import cn.cityre.mis.city.merchant.entity.po.Qd_contract;
import cn.cityre.mis.city.merchant.entity.po.Qd_contract_history;
import cn.cityre.mis.city.merchant.entity.po.UserList;
import cn.cityre.mis.city.merchant.service.UserApplyCheckHouseService;
import cn.cityre.mis.core.merchant.dao.CoreSysuserDao;
import com.dsdl.eidea.util.LikeUtil;
import com.dsdl.eidea.util.Page;
import com.dsdl.eidea.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "userApplyCheckHouseService")
public class UserApplyCheckHouseServiceImpl implements UserApplyCheckHouseService {
	@Autowired
	private Qd_contractDao qd_contractDao;
	@Autowired
	private UserListDao userListDao;
	@Autowired
	private CoreSysuserDao coreSysuserDao;

	@Override
	public List<Qd_contract> userApplyCheckHouseList(String startDate,
													 String endDate, String phone, String status, Page pager) {
		List<Qd_contract> list = new ArrayList<Qd_contract>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startDate",startDate);
		param.put("endDate",endDate);
		param.put("phone", LikeUtil.getLike(phone));
		param.put("status",status);
		param.put("startDate",startDate);
		param.put("limitStart",pager.getStart());
		param.put("limitEnd",pager.getLimit());
		int count = qd_contractDao.selectQd_contractCount(param);
		pager.setTotalCount(count);
		if(count >0){
			list = qd_contractDao.selectQd_contractList(param);
			for(Qd_contract q : list){

				//地址
				StringBuffer address = new StringBuffer();
				if(StringUtil.isNotEmpty(q.getDistrict())){
					address.append(q.getDistrict());
				}
				if(StringUtil.isNotEmpty(q.getStreet())){
					address.append(q.getStreet());
				}
				if(StringUtil.isNotEmpty(q.getStno())){
					address.append(q.getStno());

				}
				if(StringUtil.isNotEmpty(q.getHa())){
					address.append(q.getHa());

				}
				if(StringUtil.isNotEmpty(q.getBldgno())){
					address.append(q.getBldgno()+"栋");

				}
				if(StringUtil.isNotEmpty(q.getUnit())){
					address.append(q.getUnit()+"单元");

				}
				if(StringUtil.isNotEmpty(q.getRoomno())){
					address.append(q.getRoomno()+"室");
				}
				q.setAddress(address.toString());
				//
				StringBuffer situation = new StringBuffer();
				if(q.getMisLoanDate() != null && q.getMisBankName() != null){
					SimpleDateFormat sdfh = new SimpleDateFormat("yyyy年MM月dd日");
					String date = sdfh.format(q.getMisLoanDate());
					situation.append("已于");
					situation.append(date);
					situation.append("在"+q.getMisBankName()+"放款成功,");
					situation.append("放款金额为：");
					situation.append(q.getMisLoanAmount().toString()+"万元");
					q.setSituation(situation.toString());
				}

				//银行信息
				if(q.getBankUID() != null && !q.getBankUID().equals("")){
					StringBuffer bankInfo = new StringBuffer();
					bankInfo.append("<font>");
					//获取银行名称
					String coname = qd_contractDao.selectBankConameFromCoCouser(q.getBankUID());
					if(StringUtil.isNotEmpty(coname)){
						bankInfo.append( coname+"<br/>");
						q.setBankName(coname);
					}
					//获取负责人姓名
					UserList u = userListDao.selectUserlistByUid(q.getBankUID());
					if(u != null && StringUtil.isNotEmpty(u.getNickname())){
						bankInfo.append(u.getNickname());
					}

					//获取负责人电话
					String tel = qd_contractDao.selectTelNoFromUser_tel(q.getBankUID());
					if(StringUtil.isNotEmpty(tel)){
						bankInfo.append("<br/>"+tel);
					}
					bankInfo.append("</font>");
					q.setBankInfo(bankInfo.toString());
				}
				//中介信息
				if(q.getAgencyUID() != null && !q.getAgencyUID().equals("")){
					StringBuffer agencyInfo = new StringBuffer();
					agencyInfo.append("<font>");
					//获取中介名称
					String coname1 = qd_contractDao.selectAgentConameFromCoCouser1(q.getAgencyUID());
					if(StringUtil.isNotEmpty(coname1)){
						agencyInfo.append(coname1+"<br/>");
					}else{
						String coname2= qd_contractDao.selectAgentConameFromCoCouser2(q.getAgencyUID());
						if(StringUtil.isNotEmpty(coname2)){
							agencyInfo.append(coname2+"<br/>");
						}
					}
					//获取中介联系人姓名
					UserList u = userListDao.selectUserlistByUid(q.getBankUID());
					if(u != null && StringUtil.isNotEmpty(u.getNickname())){
						agencyInfo.append(u.getNickname());
					}

					//获取中介电话
					String tel = qd_contractDao.selectTelNoFromUser_tel(q.getBankUID());
					if(StringUtil.isNotEmpty(tel)){
						agencyInfo.append("<br/>"+tel);
					}
					agencyInfo.append("</font>");
					q.setAgencyInfo(agencyInfo.toString());
				}

			}
			for(Qd_contract q : list){
				if(q.getMisUID() != null){
					String name = coreSysuserDao.selectNameFromUser(q.getMisUID());
					q.setMisUserName(name);
				}
			}
		}
		return list;
	}
	@Override
	public void addBankInfo(Integer id,String bankname ,String amount,String date,Integer preStatus,String preBankName,String preAmount,String preDate,String preMisName,String sysuid){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("bankname",bankname);
		map.put("amount",amount);
		map.put("date",date);
		map.put("status",preStatus);
		map.put("preBankName",preBankName);
		map.put("preAmount",preAmount);
		map.put("preDate",preDate);
		map.put("preMisName",preMisName);
		map.put("sysuid",sysuid);
		map.put("updateTime",new Date());
		map.put("userType",0);
		map.put("misTime",new Date());
		int num = qd_contractDao.selectQd_historyCountByContractID(id);
		if(num ==0){
			//历史表之前没有相关记录，先添加一条
			qd_contractDao.insertQd_contract_history(map);
		}
		// 更新合同表
		if(preStatus == 4){
			map.put("status",4);
		}else{
			map.put("status",6);
		}
		qd_contractDao.updateQd_contractById(map);
		//向历史记录表中插入数据
		qd_contractDao.insertQd_contract_history(map);
	}
	@Override
	public void addHouseInfo(Integer id,String district,String street,String stno,String ha,String bldgno,String unit,String roomno){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("district",district);
		map.put("street",street);
		map.put("stno",stno);
		map.put("ha",ha);
		map.put("bldgno",bldgno);
		map.put("unit",unit);
		map.put("roomno",roomno);
		map.put("updatetime",new Date());
		qd_contractDao.updateQd_contractById(map);
	}
	@Override
	public List<Qd_contract_history> historyList(Integer id, Page pager){
		List<Qd_contract_history> list = new  ArrayList<Qd_contract_history>();
		int count = qd_contractDao.selectQd_contract_historyCount(id);
		pager.setTotalCount(count);
		if(count >0){
			list =qd_contractDao.selectQd_contract_historyList(id);
		}
		return list;
	}
}

package cn.cityre.mis.city.tradeProcess.entity.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Qd_contract_history implements java.io.Serializable{

	/**
	 * 自增ID
	 */
	private Integer  id;
	/**
	 * 合同ID
	 */
	private Integer contractID;
	/**
	 * Mis操作前状态
	 */
	private Integer prestatus;
	/**
	 * Mis操作状态
	 */
	private Integer status;
	/**
	 * 用户ID
	 */
	private String misUID;

	/**
	 * 放款银行
	 */
	private String bankName;
	/**
	 * 当前阶段价格
	 */
	private float amount;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 更新时间
	 */
	private String loanDate;
	
	/**
	 * mis用户名
	 */
	private String userName;
	

	
}

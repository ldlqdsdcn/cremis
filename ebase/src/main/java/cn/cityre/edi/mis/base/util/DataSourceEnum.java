package cn.cityre.edi.mis.base.util;

public enum DataSourceEnum {
	
	/**
	 * 中心库
	 */
	center("dataSource_center"),
	/**
	 * sqlserver库
	 */
	sqlserver("dataSource_sqlserver"),
	
	/**
	 * 账号中心数据库信息
	 */
	account("dataSource_account");
	
	private String value;
	
	public String value(){
		return this.value;
	}
	
	private DataSourceEnum(String value){
		this.value = value;
	}
}

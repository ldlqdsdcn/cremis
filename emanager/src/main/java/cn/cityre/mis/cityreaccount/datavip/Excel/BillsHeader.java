package cn.cityre.mis.cityreaccount.datavip.Excel;

import java.util.ArrayList;
import java.util.List;


public class BillsHeader implements ExcelHeader {


	public List<String[]> getHeadNames() {
		List<String[]> headNames = new ArrayList<String[]>();
		headNames.add(new String[] { "系统订单号", "购物车订单号", "支付宝帐号", "开始时间","结束时间","支付类型","支付金额","是否支付","支付时间","邮寄发票","发票类型","发票抬头","纳税人识别号","地址电话","开户行及账号","收件人","收件类型","发送地址","联系电话","支付人","支付电话",
		"发票号","开票日期","操作"});
		return headNames;
	}

	public List<String[]> getFieldNames() {
		// TODO Auto-generated method stub
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "billCode", "bigBillCode", "alipayBillCode", "startTime","endTime","typeName","productCost","payFlag", "payUpdateTime", "typeName", "invoiceType","invoiceTitle","invoiceTaxNo","invoiceAdTel","invoiceBankNo","postUser","postTypeName","address","tel","wPayUser","wPayTel","invoiceNo","kpInvoiceTime","userTypeName"});
		return fieldNames;
	}


	
}

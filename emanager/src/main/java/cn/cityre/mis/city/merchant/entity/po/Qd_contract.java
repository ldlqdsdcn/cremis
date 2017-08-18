package cn.cityre.mis.city.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Qd_contract implements java.io.Serializable{

	/**
	 * 自增ID
	 */
	private Integer  id;
	/**
	 * 交易码qd_forsale
	 */
	private String dealCode;
	/**
	 * 
	 */
	private String originid;
	/**
	 * 行政区
	 */
	private String district;
	/**
	 * 街道
	 */
	private String street;
	
	/**
	 *门牌号
	 */
	private String stno;
	
	/**
	 *小区
	 */
	private String ha;
	
	/**
	 *栋
	 */
	private String bldgno;
	
	/**
	 *单元
	 */
	private String unit;
	
	/**
	 *室
	 */
	private String roomno;
	
	/**
	 * 建筑面积
	 */
	private BigDecimal bldgArea;
	
	/**
	 * 楼层
	 */
	private Integer floor;
	
	/**
	 * 楼高
	 */
	private Integer height;
	
	/**
	 * 挂牌价格
	 */
	private BigDecimal price;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	
	/**
	 *客户ID
	 */
	private String customerUID;
	
	/**
	 *联系人姓名
	 */
	private String customerName;
	
	
	/**
	 *联系人电话
	 */
	private String customerTel;
	
	/**
	 *看房时间
	 */
	private String bookTime;
	/**
	 *中介uid
	 */
	private String agencyUID;
	/**
	 *合同价格
	 */
	private BigDecimal contractPrice;
	/**
	 *中介备注
	 */
	private String agencyNote;
	/**
	 *签约日期
	 */
	private String contractDate;
	/**
	 *合同更新日期
	 */
	private String contractTime;
	/**
	 *银行uid
	 */
	private String bankUID;
	
	/**
	 *贷款金额
	 */
	private BigDecimal loanAmount;
	
	/**
	 *银行备注
	 */
	private String bankNote;
	
	/**
	 *放款日期
	 */
	private String loanDate;
	
	/**
	 *放款更新时间
	 */
	private String loanTime;
	
	/**
	 *受理人
	 */
	private String misUID;
	/**
	 *受理人
	 */
	private String misUserName;
	
	/**
	 *受理
	 */
	private Integer misIsConfirm;
	
	/**
	 *放款银行(mis填写)
	 */
	private String misBankName;
	
	/**
	 *放款金额(mis填写)
	 */
	private BigDecimal misLoanAmount;
	
	/**
	 *放款日期(mis填写)
	 */
	private Date misLoanDate;
	
	/**
	 *放款金额(mis填写)
	 */
	private String misNote;
	
	
	/**
	 *受理时间
	 */
	private String misTime;
	
	
	/**
	 *更新时间
	 */
	private String updatetime;
	
	/**
	 * 受理情况
	 */
	private String situation;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 城市代码
	 * @return
	 */
	
	private String cityCode;
	/**
	 * 社区银行信息
	 * @return
	 */
	private String bankInfo;
	
	/**
	 * 社区银行名称
	 * @return
	 */
	private String bankName;
	
	/**
	 * 社区中介信息
	 * @return
	 */
	private String agencyInfo;

	
}

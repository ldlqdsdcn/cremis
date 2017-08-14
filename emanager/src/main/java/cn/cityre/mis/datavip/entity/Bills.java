package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "bills")
@Entity
@Getter
@Setter
public class Bills implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    /**
     * 用户UId
     */
    @Column(name = "suid",length = 50)
    @Length(max = 50,message = "")
    private String suid;
    /**
     * 购物车大订单号
     */
    @Column(name = "big_billcode",length = 50)
    @Length(max = 50,message = "")
    private String bigBillCode;
    @Column(name = "bill_code",length = 50)
    @Length(max = 50,message = "")
    private String billCode;
    @Column(name = "alipay_bill_code",length = 50)
    @Length(max = 50,message = "")
    private String alipayBillCode;
    @Column(name = "post_invoice_flag",length = 1)
    private Integer postInvoiceFlag;
    @Column(name = "product_code",length = 50)
    @Length(max = 50,message = "")
    private String productCode;
    @Column(name = "product_num",length = 11)
    private Integer productNum;
    @Column(name = "product_cost")
    private Double productCost;
    @Column(name = "product_remark",length = 1000)
    @Length(max = 1000,message = "")
    private String productRemark;
    @Column(name = "invoice_type",length = 1)
    private Integer invoiceType;
    @Column(name = "invoice_title",length = 200)
    @Length(max = 200,message = "")
    private String invoiceTitle;
    @Column(name = "post_user",length = 50)
    @Length(max = 50,message = "")
    private String postUser;
    @Column(name = "post_type",length = 10)
    @Length(max = 10,message="")
    private String postType;
    @Column(name = "address",length = 300)
    @Length(max = 300,message = "")
    private String address;
    @Column(name = "tel",length = 100)
    @Length(max = 100,message = "")
    private String tel;
    @Column(name = "addtime")
    private Date addTime;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "pay_flag",length = 1)
    private Integer payFlag;
    @Column(name = "pay_updatetime")
    private Date payUpdateTime;
    @Column(name = "w_pay_type",length = 10)
    @Length(max = 10,message = "")
    private String wPayType;
    @Column(name = "w_pay_user",length = 50)
    @Length(max = 50,message = "")
    private String wPayUser;
    @Column(name = "w_pay_tel",length = 200)
    @Length(max = 200,message = "")
    private String wPayTel;
    @Column(name = "invoice_no",length = 50)
    @Length(max = 50,message = "")
    private String invoiceNo;
    @Column(name = "kp_invoice_time")
    private Date kpInvoiceTime;
    @Column(name = "invoice_no_uid",length = 50)
    @Length(max = 50,message = "")
    private String invoiceNoUid;
    @Column(name = "invoice_no_time")
    private Date invoiceNoTime;
    @Column(name = "invoice_no_flag",length = 11)
    private Integer invoiceNoFlag;
    @Column(name = "invoice_taxno",length = 50)
    @Length(max = 50,message = "")
    private String invoiceTaxNo;
    @Column(name = "invoice_adtel",length = 100)
    @Length(max = 100,message = "")
    private String invoiceAdTel;
    @Column(name = "invoice_bankno",length = 100)
    @Length(max = 100,message = "")
    private String invoiceBankNo;
    @Column(name = "autoopen_url",length = 300)
    @Length(max = 300,message = "")
    private String autoOpenUrl;
    @Column(name = "city_code",length = 300)
    @Length(max = 300,message = "")
    private String cityCode;
    @Column(name = "source",length = 11)
    private Integer source;
    @Column(name = "pkey",length = 100)
    @Length(max = 100,message = "")
    private String pKey;
    @Column(name = "plevel",length = 11)
    private Integer pLevel;
    @Column(name = "dist_code",length = 10)
    @Length(max = 10,message="")
    private String distCode;
    @Column(name = "ha_code",length = 50)
    @Length(max = 50,message = "")
    private String haCode;
    @Column(name = "pgps",length = 50)
    @Length(max = 50,message = "")
    private String pgps;
    @Column(name = "buymonth",length = 200)
    @Length(max = 200,message = "")
    private String buyMonth;
    @Column(name = "haclcode",length = 200)
    @Length(max = 200,message = "")
    private String haclCode;
    @Column(name = "buygroup",length = 200)
    @Length(max = 200,message = "")
    private String buyGroup;
    @Column(name = "dealtype",length = 11)
    private Integer dealType;
    @Column(name = "contentname",length = 200)
    private String contentName;
    @Column(name = "province_code")
    private String provinceCode;


    @Transient
    private UserPaymentInfo userPaymentInfo;
    @Transient
    private DicUserType dicUserType;
    @Transient
    private UserList userList;
    @Transient
    private DicPostType dicPostType;
    @Transient
    private DicPayType dicPayType;
    @Transient
    private Integer serviceState;
    @Transient
    private Date startTime;
    @Transient
    private Date endTime;
    @Transient
    private String typeName;
    @Transient
    private String postTypeName;
    @Transient
    private String userTypeName;
    @Transient
    private String uid;




}

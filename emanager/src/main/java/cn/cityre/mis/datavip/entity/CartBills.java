package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "cartbills")
@Entity
@Getter
@Setter
public class CartBills implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    /**
     * 用户UId
     */
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    /**
     * 购物车大订单号
     */
    @Column(name = "big_billcode",length = 50)
    @Length(max = 50,message = "")
    private String bigBillCode;
    @Column(name = "alipay_bill_code",length = 50)
    @Length(max = 50,message = "")
    private String alipayBillCode;
    @Column(name = "post_invoice_flag",length = 1)
    private Integer postInvoiceFlag;
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
    @Column(name = "invoice_taxno",length = 50)
    @Length(max = 50,message = "")
    private String invoiceTaxNo;
    @Column(name = "invoice_adtel",length = 100)
    @Length(max = 100,message = "")
    private String invoiceAdTel;
    @Column(name = "invoice_bankno",length = 100)
    @Length(max = 100,message = "")
    private String invoiceBankNo;
    @Column(name = "source",length = 11)
    private Integer source;
    @Column(name = "pkey",length = 100)
    @Length(max = 100,message = "")
    private String pKey;
}

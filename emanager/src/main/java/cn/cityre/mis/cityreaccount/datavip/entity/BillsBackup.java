package cn.cityre.mis.cityreaccount.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "bills_backup_20120821")
@Entity
@Getter
@Setter
public class BillsBackup implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",length = 11,nullable = false,unique = true)
    private Integer id;
    @Column(name = "suid")
    private String suid;

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
}

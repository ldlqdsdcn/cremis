package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "ios_payment_info")
@Entity
@Getter
@Setter
public class IosPaymentInfo  implements  java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
//    客户端,参考中心库product_key中的producttype字段
    @Column(name = "app",length = 11)
    private Integer app;
    @Column(name = "pkey",length = 50)
    @Length(max = 50,message ="")
    private String pKey;
    @Column(name = "pid",length = 100)
    @Length(max = 100,message = "")
    private String pId;
    @Column(name = "billcode",length = 50)
    @Length(max = 50,message = "")
    private String billCode;
    @Column(name = "passwd",length = 50)
    @Length(max = 50,message = "")
    private String passwd;
//    发送的验证票据
    @Column(name = "receiptdata")
    private String receiptData;
//    加密后的验证票据base64
    @Column(name = "enrdata",length = 50)
    @Length(max = 50,message = "")
    private String enrData;
    @Column(name = "istatus",length = 11)
    private Integer istatus;
    @Column(name = "receipt")
    private String receipt;
    @Column(name = "latest_receipt")
    private String latestReceipt;
    @Column(name = "latest_receipt_info")
    private String latestReceiptInfo;
    @Column(name = "buyuid",length = 50)
    @Length(max = 50,message = "")
    private String buyUid;
    @Column(name = "buytime")
    private Date buyTime;
    @Column(name = "usetime")
    private Double useTime;
}

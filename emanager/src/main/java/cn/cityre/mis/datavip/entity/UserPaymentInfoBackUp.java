package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_payment_info_bak20130128")
@Entity
@Getter
@Setter
public class UserPaymentInfoBackUp implements java.io.Serializable {   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id",nullable = false,unique = true,length = 11)
private Integer id;
    @Column(name = "suid",length = 50)
    @Length(max = 50,message = "")
    private String suid;
    @Column(name = "billcode",length = 50)
    @Length(max = 50,message = "")
    private String billCode;
    @Column(name = "account_name",length = 50)
    @Length(max = 50,message = "")
    private String accountName;
    @Column(name = "account_no",length = 50)
    @Length(max = 50,message = "")
    private String accountNo;
    @Column(name = "city_code",length = 50)
    @Length(max = 50,message = "")
    private String cityCode;
    @Column(name = "pay_type",length = 10)
    @Length(max = 10,message = "")
    private String payType;
    @Column(name = "pay_amount")
    private Double payAmount;
    @Column(name = "pay_time")
    private Date payTime;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "add_time")
    private Date addTime;
    @Column(name = "remark",length = 200)
    @Length(max = 200,message = "")
    private String remark;
    @Column(name ="flag",length = 11)
    private Integer flag;
    @Column(name = "comfirm_uid",length = 50)
    @Length(max = 50,message = "")
    private String confirmUid;
    @Column(name = "confirm_time")
    private Date confirmTime;

}

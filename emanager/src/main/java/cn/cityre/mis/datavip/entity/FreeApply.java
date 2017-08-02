package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "freeapply")
@Entity
@Setter
@Getter
public class FreeApply implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "user_type",length = 10)
    @Length(max = 10,message = "")
    private String userType;
    @Column(name = "username",length = 50)
    @Length(max = 50,message = "")
    private String userName;
    @Column(name = "tel",length = 30)
    @Length(max = 30,message = "")
    private String tel;
    @Column(name = "email",length = 100)
    @Length(max = 100,message = "")
    private String email;
    @Column(name = "citycode",length = 10)
    @Length(max = 10,message = "")
    private String cityCode;
//    申请人所在单位
    @Column(name = "company",length = 200)
    @Length(max = 200,message = "")
    private String company;
    @Column(name = "industry_code",length = 10)
    @Length(max = 10,message = "")
    private String industryCode;
    @Column(name = "address",length = 500)
    @Length(max = 500,message = "")
    private String address;
//    申请人职务
    @Column(name = "job",length = 100)
    @Length(max = 100,message = "")
    private String job;
//    数据需求
    @Column(name = "datademand",length = 1500)
    @Length(max = 1500,message = "")
    private String dataDemand;
//    数据用途
    @Column(name = "datause",length = 1500)
    @Length(max = 1500,message = "")
    private String dataUse;
    @Column(name = "bill_code",length = 50)
    @Length(max = 50,message = "")
    private String billCode;
    @Column(name = "updatetime")
    private Date updateTime;
//    申请平台creprice:房价平台
    @Column(name = "platform",length = 10)
    @Length(max = 10,message = "")
    private String platform;
    @Column(name = "flag",length = 11)
    private Integer flag;
    @Column(name = "confirmuid",length = 50)
    @Length(max = 50,message = "")
    private String comfirmUid;
    @Column(name = "confirmtime")
    private Date confirmTime;
    @Column(name = "confirmremark",length = 300)
    @Length(max = 300,message = "")
    private String confirmRemark;
}

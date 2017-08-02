package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_list")
@Entity
@Getter
@Setter
public class UserList implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "suid",length = 50)
    @Length(max = 50,message = "")
    private String suid;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "pwd",length = 50)
    @Length(max = 50,message = "")
    private String pwd;
    @Column(name = "username",length = 50)
    @Length(max = 50,message = "")
    private String userName;
    @Column(name = "sex",length = 20)
    @Length(max = 20,message = "")
    private String sex;
    @Column(name = "regtime")
    private Date regTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private  String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "email",length = 120)
    @Length(max = 120,message = "")
    private String email;
    @Column(name = "user_type",length = 10)
    @Length(max = 10,message = "")
    private String userType;
    @Column(name = "pay_user",length = 50)
    @Length(max = 50,message = "")
    private String payUser;
    @Column(name = "pay_type",length = 10)
    @Length(max = 10,message = "")
    private String payType;
    @Column(name = "pay_tel",length = 200)
    @Length(max = 200,message = "")
    private String payTel;
    @Column(name = "pay_remark",length = 300)
    @Length(max = 300,message = "")
    private String payRemark;
    @Column(name = "remark",length = 1000)
    @Length(max = 1000,message = "")
    private String remark;
    @Column(name = "usource",length = 50)
    @Length(max = 50,message = "")
    private String usource;
    @Column(name = "flag",length = 11)
    private Integer flag;
    @Column(name = "checkuid",length = 50)
    @Length(max = 50,message = "")
    private String checkUid;
    @Column(name = "checktime")
    private Date checkTime;
    @Column(name = "company",length = 200)
    @Length(max = 200,message = "")
    private String company;
//    代理商
    @Column(name = "agent",length = 200)
    @Length(max = 200,message = "")
    private String agent;
    @Column(name = "telflag",length = 11)
    private Integer telFlag;
    @Column(name = "emailflag",length = 11)
    private Integer emailflag;
    @Column(name = "usericon",length = 50)
    @Length(max = 50,message = "")
    private String usericon;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "citycode",length = 10)
    @Length(max = 10,message = "")
    private String cityCode;
    @Column(name = "nickName",length = 50)
    @Length(max = 50,message = "")
    private String nickName;
//    自动注册标识：1=自注册，0=正 常注册
    @Column(name = "autoflag",length = 1)
    private Integer autoFlag;
}

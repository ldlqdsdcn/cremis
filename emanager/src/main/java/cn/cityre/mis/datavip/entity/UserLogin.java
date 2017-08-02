package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "userlogin")
@Getter
@Setter
@Entity
public class UserLogin implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "sn",length = 64)
    @Length(max = 64,message = "")
    private String sn;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "user_type",length = 10)
    @Length(max = 10,message = "")
    private String userType;
    @Column(name = "logintime")
    private Date loginTime;
    @Column(name = "city",length = 50)
    @Length(max = 50,message = "")
    private String city;
    @Column(name = "versionname",length = 50)
    @Length(max = 50,message = "")
    private String versionName;
    @Column(name = "version",length = 50)
    @Length(max = 50,message = "")
    private String version;
    @Column(name = "remark",length = 100)
    @Length(max = 100,message = "")
    private String remark;
    @Column(name = "ip",length = 20)
    @Length(max = 20,message = "")
    private String ip;
    @Column(name = "browser",length = 100)
    @Length(max = 100,message = "")
    private String browser;
//    操作系统
    @Column(name = "os",length = 100)
    @Length(max = 100,message = "")
    private String os;
}

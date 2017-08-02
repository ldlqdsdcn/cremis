package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Entity
@Table(name = "uid_token")
@Getter
@Setter
public class UidToken implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;

//    序列号
    @Column(name = "sn",length = 50)
    @Length(max = 50,message = "")
    private String sn;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "suid",length = 50)
    @Length(max = 50,message = "")
    private String suid;
    @Column(name = "pkey",length = 50)
    @Length(max = 50,message = "")
    private String pkey;
    @Column(name = "versionname",length = 20)
    @Length(max = 20,message = "")
    private String versionName;
    @Column(name = "version",length = 20)
    @Length(max = 20,message = "")
    private String version;
    @Column(name = "flag",length = 11)
    private Integer flag;
    @Column(name = "app",length = 11)
    private Integer app;
    @Column(name = "remark",length = 50)
    @Length(max = 50,message = "")
    private String remark;
    @Column(name = "adduid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "addtime")
    private Date addTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name ="updatetime")
    private Date updateTime;
}


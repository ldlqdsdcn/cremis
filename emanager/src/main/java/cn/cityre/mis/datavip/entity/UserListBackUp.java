package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "userlist_backup_20120810")
@Entity
@Getter
@Setter
public class UserListBackUp implements java.io.Serializable {
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
    @Column(name = "flag",length = 11)
    private Integer flag;
    @Column(name = "checkuid",length = 50)
    @Length(max = 50,message = "")
    private String checkUid;
    @Column(name = "checktime")
    private Date checkTime;
}

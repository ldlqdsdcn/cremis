package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_update_log")
@Entity
@Getter
@Setter
public class UserUpdateLog implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    @Column(name = "log_code", length = 50)
    @Length(max = 50,message = "")
    private String logCode;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "uid_type",length = 50)
    @Length(max = 50,message = "")
    private String uidType;
    @Column(name = "beforeinfo",length = 100)
    @Length(max = 100,message = "")
    private String beforeInfo;
    @Column(name = "afterinfo",length = 100)
    @Length(max = 100,message = "")
    private String afterInfo;
    @Column(name = "field",length = 100)
    @Length(max = 100,message = "")
    private String field;
    @Column(name = "log_type",length = 11)
    private Integer logType;
    @Column(name = "update_uid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "update_time")
    private Date updateTime;
}

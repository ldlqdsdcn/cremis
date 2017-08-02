package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_permission")
@Entity
@Getter
@Setter
public class UserPermission implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "city",length = 50)
    @Length(max = 50,message = "")
    private String city;
    @Column(name = "permission",length = 50)
    @Length(max = 50,message = "")
    private String permission;
    @Column(name = "flag",length = 1)
    private Integer flag;
    @Column(name = "remark",length = 50)
    @Length(max = 50,message = "")
    private String remark;
    @Column(name = "add_uid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "add_time")
    private Date addTime;
    @Column(name = "update_uid",length = 50)
    @Length(max = 50,message ="" )
    private String updateUid;
    @Column(name = "update_time")
    private Date updateTime;
}

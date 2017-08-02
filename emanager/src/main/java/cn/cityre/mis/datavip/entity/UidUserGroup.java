package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 用户与用户组关联表
 */
@Table(name = "uid_usergroup")
@Entity
@Getter
@Setter
public class UidUserGroup implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
//    用户组别标识
    @Column(name = "usergid",length = 50)
    @Length(max = 50,message = "")
    private String userGid;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuid",length = 50)
    @Length(max = 50,message = "")
    private String createUid;
}

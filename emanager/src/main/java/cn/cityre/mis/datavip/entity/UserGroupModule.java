package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "usergroup_module")
@Entity
@Setter
@Getter
public class UserGroupModule implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    @Column(name = "usergid", length = 50)
    @Length(max = 50, message = "")
    private String userGid;
    @Column(name = "amcode", length = 50)
    @Length(max = 50, message = "")
    private String amCode;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuid", length = 50)
    @Length(max = 50, message = "")
    private String createUid;
}

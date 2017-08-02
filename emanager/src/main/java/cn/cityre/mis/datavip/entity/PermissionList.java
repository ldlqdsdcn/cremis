package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "permission_list")
@Entity
@Getter
@Setter
public class PermissionList implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    //    权限码，规则：部门(xx)+主业务(xxx)+次级业务(xxx)
    @Column(name = "perm_code", length = 50)
    @Length(max = 50, message = "")
    private String permCode;
    @Column(name = "perm_name", length = 100)
    @Length(max = 100, message = "")
    private String permName;
    @Column(name = "perm_url", length = 100)
    @Length(max = 100, message = "")
    private String permUrl;
    @Column(name = "parent_perm", length = 50)
    @Length(max = 50, message = "")
    private String parentPerm;
    @Column(name = "showorder", length = 6)
    private Integer showOrder;
    @Column(name = "remark", length = 100)
    @Length(max = 100, message = "")
    private String remark;
    @Column(name = "add_uid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "add_time")
    private Date addTime;
    @Column(name = "update_uid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
}

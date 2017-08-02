package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_group")
@Entity
@Getter
@Setter
public class UserGroup implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "usergid",length = 50)
    @Length(max = 50,message = "")
    private String usergid;
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message = "")
    private String companyCode;
    @Column(name = "usergname",length = 100)
    @Length(max = 100,message = "")
    private String usergName;
    @Column(name = "usergdesc",length = 200)
    @Length(max = 200,message = "")
    private String usergDesc;
    @Column(name = "numorder",length = 5)
    private Integer numOrder;
    @Column(name = "createuid",length = 50)
    private String createUid;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "enabledflag")
    private String enabledFlag;
}

package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "company_dept")
@Entity
@Getter
@Setter
public class CompanyDept implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "dept_code",length = 50)
    @Length(max = 50,message="")
    private String deptCode;
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message = "")
    private String companyCode;
    @Column(name = "deptname",length = 50)
    @Length(max = 50,message = "")
    private String deptName;
    @Column(name = "deptdesc",length = 200)
    @Length(max = 200,message = "")
    private String deptDesc;
    @Column(name = "linkman",length = 30)
    @Length(max = 30,message = "")
    private String linkMan;
    @Column(name = "mobile",length = 13)
    @Length(max = 13,message = "")
    private String mobile;
    /**
     * 部门级别：1为最低，
     * 数据库字段为numlevel
     */
    @Column(name = "numlevel",length = 5)
    private Integer deptLevel;
    @Column(name = "createuid",length = 50)
    @Length(max = 50,message = "")
    private String createUid;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "email",length = 200)
    @Length(max = 200,message = "")
    private String email;
    /**
     * 是否有效标识，Y:有效、N：无效
     */
    @Column(name = "enabledflag",length = 1)
    @Length(max = 1,message = "")
    private String enabledFlag;
}

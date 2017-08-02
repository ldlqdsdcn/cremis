package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "company_cert")
@Entity
@Getter
@Setter
public class CompanyCert implements java.io.Serializable {
    @Id
    private Integer id;
    /**
     * 公司认证Code
     */
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message="")
    private String companyCertCode;
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message = "")
    private String companyCode;
    @Column(name = "company_name",length = 200)
    @Length(max = 200,message = "")
    private String companyName;
    @Column(name = "company_desc",length = 500)
    @Length(max = 500,message = "")
    private String companyDesc;
    @Column(name = "legal_person",length = 30)
    @Length(max = 30,message="")
    private String legalPerson;
    @Column(name = "industry",length = 50)
    @Length(max = 50,message = "")
    private String industry;
    @Column(name = "linkman",length = 30)
    @Length(max = 30,message = "")
    private String linkMan;
    @Column(name = "mobile",length = 13)
    @Length(max = 13,message = "")
    private String mobile;
    @Column(name = "email",length = 200)
    @Length(max = 200,message = "")
    private String email;
    @Column(name = "department",length = 50)
    @Length(max = 50,message = "")
    private String department;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "siteurl",length = 50)
    @Length(max = 50,message = "")
    private String siteUrl;
    @Column(name = "address",length = 200)
    @Length(max = 200,message = "")
    private String address;
    @Column(name = "company_type",length = 2)
    @Length(max = 2,message = "")
    private Integer companyType;
    /**
     * 销售状态：1:咨询客户,2:试用客户,3:成交客户
     */
    @Column(name = "salestatus",length = 2)
    private Integer saleStatus;
    /**
     * 审核状态:-1 审核失败，0 待审核，1 审核成功
     */
    @Column(name = "auditstatus",length = 2)
    private Integer auditStatus;

    /**
     * 审核意见
     */
    @Column(name = "audit_opinion",length = 300)
    @Length(max = 300,message = "")
    private String auditOpinion;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    /**
     * 审核人
     */
    @Column(name = "reviewer",length = 50)
    @Length(max = 50,message = "")
    private String reviewer;
    @Column(name = "audittime")
    private Date auditTime;
}

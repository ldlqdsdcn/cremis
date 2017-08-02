package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "company")
@Entity
@Getter
@Setter
public class Company implements java.io.Serializable {
    @Id
    /**
     * 流水号
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "company_code",length = 50)
    @Length(max = 50,message = "")
    private String companyCode;
    @Column(name = "company_name",length = 200)
    @Length(max = 200,message = "")
    private String companyName;
    /**
     * 公司描述
     */
    @Column(name = "company_desc",length = 500)
    @Length(max = 500,message = "")
    private String companyDesc;
    @Column(name = "legal_person",length = 30)
    @Length(max = 30,message = "")
    private String legalPerson;
    @Column(name = "parent_code",length = 50)
    @Length(max = 50,message = "")
    private String parentCode;
    /**
     * 单位所属行业
     */
    @Column(name = "industry",length = 50)
    @Length(max = 50,message = "")
    private String industry;
    @Column(name = "city_code",length = 10)
    @Length(max = 10,message = "")
    private String cityCode;
    @Column(name = "dist_code",length = 10)
    @Length(max = 10,message = "")
    private String distCode;
    @Column(name = "linkman",length = 30)
    @Length(max = 30,message = "")
    private String linkMan;
    @Column(name = "mobile",length = 13)
    @Length(max = 13,message = "")
    private String mobile;
    @Column(name = "email",length = 200)
    @Length(max = 200,message = "")
    private String email;
    @Column(name = "siteurl",length = 200)
    @Length(max = 200,message = "")
    private String siteUrl;
    @Column(name = "address",length = 200)
    @Length(max = 200,message = "")
    private String address;
    /**
     * 公司类型1:独立机构,2:有子公司或分支机构
     */
    @Column(name = "company_type",length = 2)
    private Integer companyType;
    @Column(name = "salestatus",length = 2)
    private Integer saleStatus;
    /**
     * 是否可读下一级默认为N
     */
    @Column(name = "isread",length = 1)
    @Length(max = 1,message = "")
    private String isRead;
    /**
     * 公司级别：0上级，1下级
     */
    @Column(name = "level",length = 5)
    private Integer level;
    @Column(name = "offeruid",length = 50)
    @Length(max = 50,message = "")
    private String offerUid;
    @Column(name = "offertime")
    private Date offerTime;
    @Column(name = "updateUid",length = 50)
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    /**
     * 是否有效   Y 有效  N无效
     */
    @Column(name = "enabledflag",length = 1)
    private String enabledFlag;
    /**
     * 计费标准{1，本级公司；2，上级公司}
     */
    @Column(name = "feellevel")
    private String feeLevel;
}

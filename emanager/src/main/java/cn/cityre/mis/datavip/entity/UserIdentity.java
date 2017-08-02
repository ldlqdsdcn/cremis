package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_identity")
@Entity
@Getter
@Setter
public class UserIdentity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "cardnum",length = 50)
    @Length(max = 50,message = "")
    private String cardnum;
//职业身份
    @Column(name = "profession",length = 50)
    @Length(max = 50,message = "")
    private String profession;
    @Column(name = "filename",length = 50)
    @Length(max = 50,message = "")
    private String fileName;
    @Column(name = "company_name",length = 200)
    @Length(max = 200,message = "")
    private String companyName;
    @Column(name = "company_industry",length = 200)
    @Length(max = 200,message = "")
    private String companyIndustry;
//    审核状态：-1 审核失败，0=未认证,2=审核中    默认 0
    @Length(max = 200,message = "")
    private  Integer vc2status;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "updatedate")
    private Date updateDate;
    @Column(name = "reviewer",length = 50)
    @Length(max = 50,message = "")
    private String reviewer;
    @Column(name = "opinion",length = 50)
    private String opinion;
    @Column(name = "audittime")
    private  Date auditTime;
}

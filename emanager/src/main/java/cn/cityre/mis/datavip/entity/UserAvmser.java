package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_avmser")
@Entity
@Setter
@Getter
public class UserAvmser implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "apikey",length = 40)
    @Length(max = 40,message = "")
    private String apikey;
    @Column(name = "usercode",length = 50)
    @Length(max = 50,message = "")
    private String userCode;
    @Column(name = "goodsid",length = 50)
    @Length(max = 50,message = "")
    private String goodsId;
    @Column(name = "city",length = 50)
    @Length(max = 50,message = "")
    private String city;
    @Column(name = "begindate")
    private Date beginDate;
    @Column(name = "enddate")
    private Date endDate;
    @Column(name = "numtimes",length = 19)
    private Integer numTimes;
    @Column(name = "numbalance",length = 9)
    private Integer numBalance;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "adduid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "updatedate")
    private Date updateDate;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
//    备注
    @Column(name = "vcdesc",length = 500)
    @Length(max = 500,message = "")
    private String vcDesc;
//客户角色标识：1:个人用户，2：企业用户
    @Column(name = "flag",length = 1)
    private Integer flag;
}

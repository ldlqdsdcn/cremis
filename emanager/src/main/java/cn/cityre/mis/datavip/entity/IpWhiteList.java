package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "ip_whitelist")
@Entity
@Setter
@Getter
public class IpWhiteList implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "vcip",length = 30)
    @Length(max = 30,message = "")
    private String vcip;
    @Column(name = "apikey",length = 50)
    @Length(max = 50,message = "")
    private String apikey;
    @Column(name = "note",length = 500)
    @Length(max = 500,message = "")
    private String note;
    @Column(name = "datestart")
    private Date dateStart;
    @Column(name = "dateend")
    private Date dateEnd;
    @Column(name = "adduid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "enabledflag",length = 1)
    @Length(max = 1,message = "")
    private String enabledFlag;
}

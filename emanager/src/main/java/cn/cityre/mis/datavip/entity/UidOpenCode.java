package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "uid_opencode")
@Entity
@Getter
@Setter
public class UidOpenCode implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
@Column(name = "uid",length = 50)
@Length(max = 50,message = "")
    private String uid;
//    第三方用户ID
    @Column(name = "openuid",length = 1000)
    @Length(max = 1000,message = "")
    private String openUid;
    @Column(name = "unionid",length = 100)
    @Length(max = 100,message = "")
    private String unionId;
    @Column(name = "appid",length = 45)
    @Length(max = 45,message = "")
    private String appId;
    @Column(name = "opensource",length = 50)
    @Length(max = 50,message = "")
    private String openSource;
    @Column(name = "apikey",length = 50)
    @Length(max = 50,message = "")
    private String apikey;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updatetime")
    private Date updateTime;
}

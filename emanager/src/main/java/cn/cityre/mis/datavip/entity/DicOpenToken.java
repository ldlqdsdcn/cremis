package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 用户第三方应用token表
 */
@Table(name = "dic_opentoken")
@Setter
@Getter
@Entity
public class DicOpenToken implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
//    第三方应用token
    @Column(name = "opentoken",length = 1000)
    @Length(max = 1000,message = "")
    private String openToken;
//    第三方应用标识
    @Column(name = "opensource",length = 50)
    @Length(max = 50,message = "")
    private String openSource;
//    token有效时间（秒）
    @Column(name = "expires",length = 9)
    private Integer expires;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "updatetime")
    private Date updateTime;
}

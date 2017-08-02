package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "app_product")
@Entity
@Getter
@Setter
public class AppProduct implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    /**
     * 参考中心库product_key中的productType字段
     */
    @Column(name = "app", length = 11)
    private Integer app;
    @Column(name = "pid",length = 100)
    @Length(max = 100,message = "")
    private String pId;
    @Column(name = "pname",length = 50)
    @Length(max = 50,message = "")
    private String pName;
    @Column(name = "refername",length = 255)
    @Length(max = 255,message="")
    private String referName;
    @Column(name = "ptype",length = 100)
    @Length(max = 100,message = "")
    private String pType;
    @Column(name = "pnote",length = 100)
    @Length(max = 100,message = "")
    private String pNote;
    @Column(name = "planguage",length = 20)
    @Length(max = 20,message = "")
    private String pLanguage;
    @Column(name = "flag",length = 11)
    @Length(max = 11,message="")
    private Integer flag;
    @Column(name = "adduid",length = 50)
    @Length(max = 50,message = "")
    private String addUid;
    @Column(name = "addtime")
    private Date addTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
}

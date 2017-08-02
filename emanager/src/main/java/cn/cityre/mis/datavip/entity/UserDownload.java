package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_download")
@Entity
@Getter
@Setter
public class UserDownload implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "unionUid",length = 64)
    @Length(max = 64,message = "")
    private String unionUid;
    @Column(name = "productCode",length = 32)
    @Length(max = 32,message = "")
    private String productCode;
    @Column(name = "orderCode",length = 64)
    @Length(max = 64,message = "")
    private String orderCode;
    @Column(name = "orderServiceId",length = 10)
    private Integer orderServiceId;
    @Column(name = "downloadCode",length = 64)
    @Length(max = 64,message = "")
    private String downloadCode;
    @Column(name = "fileKey",length = 64)
    @Length(max = 64,message = "")
    private String fileKey;
    @Column(name = "fileName",length = 128)
    @Length(max = 128,message = "")
    private String fileName;
    @Column(name = "fileSecret",length = 32)
    @Length(max = 32,message = "")
    private  String fileSecret;
    @Column(name = "title",length = 64)
    @Length(max = 64,message = "")
    private String title;
    @Column(name = "description",length = 1024)
    @Length(max = 1024,message = "")
    private String description;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "expireTime")
    private Date exprieTime;
}

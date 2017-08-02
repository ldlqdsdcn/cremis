package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 功能模块表
 */
@Table(name = "dic_module")
@Entity
@Setter
@Getter
public class DicModule implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
//    模块CODE
    @Column(name = "amcode",length = 50)
    @Length(max = 50,message = "")
    private String amCode;

//    模块组织标识
    @Column(name = "amgrp",length = 50)
    @Length(max = 50,message = "")
    private String amGrp;
    @Column(name = "amname",length = 50)
    @Length(max = 50,message = "")
    private String amName;
    @Column(name = "amdesc",length = 200)
    @Length(max = 200,message = "")
    private String amDesc;
    @Column(name = "amlevel",length = 5)
    private Integer amLevel;
    @Column(name = "permission",length = 100)
    @Length(max = 100,message = "")
    private String permission;
    @Column(name = "productcode",length = 50)
    @Length(max = 50,message = "")
    private String productCode;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuid",length = 50)
    @Length(max = 50,message = "")
    private String createUid;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
    @Column(name = "enabledflag",length = 1)
    @Length(max = 1,message = "")
    private String enabledFlag;
}

package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "dic_user_type_cost")
@Entity
@Getter
@Setter
public class DicUserTypeCost implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "user_type",length = 10)
    @Length(max = 10,message = "")
    private String userType;
//费用,单位均已元为单位
    @Column(name = "cost",length = 30)
    @Length(max = 30,message = "")
    private String cost;
//    格式年、月、日分别为y、m、d，如3年表示为3y，一个月表示为1m
    @Column(name = "cycle",length = 30)
    @Length(max = 30,message = "")
    private String cycle;
//    资费详情，采用json格式
    @Column(name = "remark",length = 100)
    @Length(max = 100,message = "")
    private String remark;
    @Column(name = "note")
    private String note;
    @Column(name = "adduid",length = 30)
    @Length(max = 30,message = "")
    private String addUid;
    @Column(name = "addtime")
    private Date addTime;
    @Column(name = "updateuid",length = 50)
    @Length(max = 50,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;
}

package cn.cityre.edi.mis.base.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/12.
 */
@Table(name = "v2017_user_phone",catalog = "cre_mis")
@Entity
@Getter
@Setter
public class MisUserPhonePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 10)
    private Integer id;
    @Column(name = "unionUid",nullable = false,length = 64,unique = true)
    private String unionUid;
    @Column(name = "phone",nullable = false,unique = true,length = 20)
    private String phone;
    @Column(name = "isVerified",nullable = false,length = 4)
    private Byte isVerified;
    @Column(name = "isPrimary",nullable = false,length = 4)
    private Byte isPrimary;
    @Column(name = "createTime",nullable = false)
    private Date createTime;
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}

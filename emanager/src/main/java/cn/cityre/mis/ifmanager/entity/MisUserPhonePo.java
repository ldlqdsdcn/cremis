package cn.cityre.mis.ifmanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/12.
 */
@Table(name = "v2017_user_phone")
@Entity
@Getter
@Setter
public class MisUserPhonePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 10)
    private Integer id;
    @Column(name = "unionUid",nullable = false,length = 64,unique = true)
    @NotBlank(message = "error.v2017.user.unionUid.not_null")
    @Length(min = 1,max = 64,message = "base.v2017.email.unionUid.length")
    private String unionUid;
    @Column(name = "phone",nullable = false,unique = true,length = 20)
    @Length(min = 11,max = 20,message = "base.v2017.phonepin.phone.length")
    @NotBlank(message = "base.v2017.phonepin.phone.length.not_null")
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

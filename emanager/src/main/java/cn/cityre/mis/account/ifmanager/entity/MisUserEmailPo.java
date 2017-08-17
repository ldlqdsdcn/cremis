package cn.cityre.mis.account.ifmanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/12.
 */
@Table(name = "v2017_user_email")
@Entity
@Getter
@Setter
public class MisUserEmailPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 10)
    private Integer id;

    @Column(name = "unionUid", nullable = false, length = 64, unique = true)
    @Length(min = 1,max = 64,message = "base.v2017.email.unionUid.length")
    @NotBlank(message = "error.v2017.user.unionUid.not_null")
    private String unionUid;
    @Column(name = "address", length = 128,nullable = false)
    @NotBlank(message = "error.v2017.email.adderss.not_null")
    @Length(min = 1,max = 128,message = "base.v2017.email.address.length")
    private String address;
    @Column(name = "isVerified", length = 4,nullable = false)
    private Byte isVerified;
    @Column(name = "isPrimary", length = 4,nullable = false)
    private Byte isPrimary;
    @Column(name = "pinToken", length = 64,nullable = false)
    @Length(min = 1,max = 64,message = "base.v2017.email.pinToken.length")
    @NotBlank(message = "error.v2017.email.pinToken.not_null")
    private String pinToken;
    @Column(name = "createTime",nullable = false)
    private Date createTime;
    @Column(name = "expireTime",nullable = false)
    private Date expireTime;
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}

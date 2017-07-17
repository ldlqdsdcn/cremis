package cn.cityre.edi.mis.base.entity.po;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Table(name = "v2017_user",catalog = "cre_mis")
@Entity
@Getter
@Setter
public class MisUserPo implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 10)
    private Integer id;
    @Column(name = "unionUid",nullable = false,unique = true,length = 64)
    @NotBlank(message = "error.v2017.user.unionUid.not_null")
    @Length(min = 1,max = 64,message = "base.v2017.email.unionUid.length")
    private String unionUid;
    /**
     * 登陆名
     */
    @Column(name = "userId",nullable = false,unique = true,length = 64)
    @NotBlank(message = "error.v2017.user.userId.not_null")
    @Length(min = 1,max = 64,message = "base.v2017.user.userId.length")
    private String userId;
    /**
     * 显示姓名（昵称）
     */
    @Column(name = "nickName",nullable = false,length = 64)
    @NotBlank(message = "error.v2017.user.nickName.not_null")
    @Length(min = 1,max = 64,message = "base.v2017.user.nickName.length")
    private String nickName;
    /**
     * 真实姓名
     */
    @Column(name = "realName",nullable = false,length = 64)
    @NotBlank(message = "error.2017.user.realName.not_null")
    @Length(min = 1,max = 64,message = "base.v2017.user.realName.length")
    private String realName;
    @Column(name ="sex",length = 4,nullable = false)
    private Byte sex;
    @Column(name = "birthday",length = 0)
    private Date birthday;
    @Column(name = "userIcon",length = 256)
    private String userIcon;
    @Column(name = "passwordSalt",length = 32,nullable = false)
    @NotBlank(message = "")
    @Length(min = 1,max = 32,message = "")
    private String passwordSalt;
    @Column(name = "passwordHash",length = 128,nullable = false)
    @NotBlank(message = "error.v2017.user.passwordSalt.not_null")
    @Length(min = 1,max = 128,message = "base.v2017.user.passwordSalt.length")
    private String passwordHash;
    @Column(name = "oldPasswordHash",length = 32)
    @Length(min = 1,max = 32,message = "base.v2017.user.oldpasswordSalt.length")
    private String oldPasswordHash;
    @Column(name = "isVerified",length = 4,nullable = false)
    private Byte isVerified;
    @Column(name = "isValid",length = 4)
    private Byte isValid;
    @Column(name = "accountSecret",length = 64,nullable = false)
    @NotBlank(message = "error.v2017.user.accountSecret.not_null")
    @Length(min = 1,max = 64,message = "base.v2017.user.accountSecret.length")
    private String accountSecret;
    @Column(name = "createTime",nullable = false)
    private Date createTime;
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}

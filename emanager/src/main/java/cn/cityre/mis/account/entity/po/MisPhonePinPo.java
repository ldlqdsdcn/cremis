package cn.cityre.mis.account.entity.po;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/12.
 */
@Table(name = "v2017_phone_pin",catalog = "cre_mis")
@Entity
@Getter
@Setter
public class MisPhonePinPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 10)
    private Integer id;
    @Column(name = "phone",nullable = false,unique = true,length = 20)
    @NotBlank(message = "base.v2017.phonepin.phone.length.not_null")
    @Length(min = 11,max = 20,message ="base.v2017.phonepin.phone.length")
    private String phone;
    @Column(name = "pinToken",nullable = false,unique = true,length = 64)
    @NotBlank(message = "base.v2017.phonepin.pinToken.not_Null")
    @Length(min =2,max = 64,message = "base.v2017.phonepin.pinToken.length")
    private String pinToken;
    @Column(name = "pinCode",nullable = false,unique = true,length = 6)
    @NotBlank(message = "base.v2017.phonepin.pinCode.not_null")
    @Length( min=6,max = 6,message = "base.v2017.phonepin.pinCode.length")
    private String pinCode;
    @Column(name = "action",length = 16,nullable = false)
    @NotBlank(message = "base.v2017.phonepin.action.not_null")
    @Length(min=2,max = 16,message = "base.v2017.phonepin.action.length")
    private String action;
    @Column(name = "createTime",nullable = false)
    private Date createTime;
    @Column(name = "expireTime",nullable = false)
    private Date expireTime;
}

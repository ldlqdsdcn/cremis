package cn.cityre.edi.mis.base.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/12.
 */
@Table(name = "v2017_user_email",catalog = "cre_mis")
@Entity
@Getter
@Setter
public class MisUserEmailPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 10)
    private Integer id;

    @Column(name = "unionUid", nullable = false, length = 64, unique = true)
    private String unionUid;
    @Column(name = "address", length = 128,nullable = false)
    private String address;
    @Column(name = "isVerified", length = 4,nullable = false)
    private Byte isVerified;
    @Column(name = "isPrimary", length = 4,nullable = false)
    private Byte isPrimary;
    @Column(name = "pinToken", length = 64,nullable = false)
    private String pinToken;
    @Column(name = "createTime",nullable = false)
    private Date createTime;
    @Column(name = "expireTime",nullable = false)
    private Date expireTime;
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}

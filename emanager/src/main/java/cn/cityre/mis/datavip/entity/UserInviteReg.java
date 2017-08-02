package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_invite_reg")
@Getter
@Setter
@Entity
public class UserInviteReg implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    @Column(name = "uid", length = 50)
    @Length(max = 50,message = "")
    private String uid;
    //    被邀请注册的UID
    @Column(name = "reguid",length = 50)
    @Length(max = 50,message = "")
    private String regUid;
    @Column(name = "pay_status",length = 5)
    private Integer payStatus;
    @Column(name = "createdate")
    private Date createDate;
    @Column(name = "updatedate")
    private Date updateDate;
    @Column(name = "apikey",length = 50)
    @Length(max = 50,message = "")
    private String apikey;
    @Column(name = "producttype",length = 11)
    private Integer productType;
}

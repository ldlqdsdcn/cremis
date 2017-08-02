package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 用户查看权限表
 */
@Table(name = "uid_readperm")
@Entity
@Getter
@Setter
public class UidReadPerm implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    @Column(name = "uid", length = 50)
    @Length(max = 50, message = "")
    private String uid;
    @Column(name = "dept_code",length = 50)
    @Length(max = 50,message = "")
    private String deptCode;
    @Column(name = "infotype",length = 1)
    @Length(max = 1,message = "")
    private String infoType;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuid",length = 50)
    @Length(max = 50,message = "")
    private String createUid;
}

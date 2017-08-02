package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "user_dept")
@Entity
@Getter
@Setter
public class UserDept implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "uid",length = 50)
    @Length(max = 50,message = "")
    private String uid;
    @Column(name = "dept_code",length = 50)
    @Length(max = 50,message = "")
    private String deptCode;
//    是否部门主管,是:'Y',否:'N'
    @Column(name = "ismanager",length = 1)
    @Length(max = 1,message = "")
    private String isManager;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuid",length = 50)
    @Length(max = 50,message = "")
    private String createUid;
}

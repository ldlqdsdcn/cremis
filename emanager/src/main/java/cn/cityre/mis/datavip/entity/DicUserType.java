package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/27.
 * 用户类型字典
 */
@Table(name = "dic_user_type")
@Entity
@Getter
@Setter
public class DicUserType implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    //    用户类型码
    @Column(name = "user_type_code", length = 10)
    @Length(max = 10, message = "")
    private String userTypeCode;
    @Column(name = "user_type_name", length = 20)
    @Length(max = 20, message = "")
    private String userTypeName;
    //    用户类型标题
    @Column(name = "user_type_list", length = 200)
    @Length(max = 200, message = "")
    private String userTypeTitle;
    //    说明
    @Column(name = "intro", length = 200)
    @Length(max = 200, message = "")
    private String intro;
    //    显示顺序
    @Column(name = "showorder", length = 11)
    private Integer showOrder;
    //    是否启用
    @Column(name = "flag", length = 11)
    private Integer flag;
    //产品系列，AMDS/BMDS/CMDS/cityhosue
    @Column(name = "product", length = 30)
    @Length(max = 30, message = "")
    private String product;
    @Column(name = "type", length = 100)
    @Length(max = 100, message = "")
    private String type;
    @Column(name = "subtype", length = 100)
    @Length(max = 100, message = "")
    private String subType;
    @Column(name = "note")
    private String note;
    @Column(name="adduid",length = 20)
    @Length(max = 20,message = "")
    private String addUid;
    @Column(name = "addtime")
    private Date addTime;
    @Column(name = "updateuid",length = 20)
    @Length(max = 20,message = "")
    private String updateUid;
    @Column(name = "updatetime")
    private Date updateTime;

}

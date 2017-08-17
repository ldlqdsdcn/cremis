package cn.cityre.mis.cityreaccount.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "dic_user_type_bak20121120")
@Entity
@Getter
@Setter
public class DicUserTypeBackUp implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Integer id;
    @Column(name = "user_type_code",length = 10)
    @Length(max = 10,message = "")
    private String userTypeCode;
    @Column(name = "user_type_title",length = 20)
    @Length(max = 200,message = "")
    private String userTypeTitle;
    @Column(name = "user_type_name",length =20 )
    @Length(max = 20,message = "")
    private String userTypeName;
    @Column(name = "intro",length = 200)
    @Length(max = 200,message = "")
    private String intro;
}

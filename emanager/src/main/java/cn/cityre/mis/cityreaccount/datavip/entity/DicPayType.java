package cn.cityre.mis.cityreaccount.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by cityre on 2017/7/27.
 */
@Entity
@Table(name = "dic_pay_type")
@Getter
@Setter
public class DicPayType implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false,length = 11)
    private Integer id;
@Column(name = "type_code",length = 10)
@Length(max = 10,message = "")
    private String typeCode;
    @Column(name = "type_name",length = 20)
    @Length(max = 20,message = "")
    private String typeName;
}

package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name="dic_log_type")
@Entity
@Getter
@Setter
public class DicLogType implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    /**
     * 操作码
     */
    @Column(name = "log_type_code",length = 11)
    private Integer logTypeCode;
    @Column(name = "log_type_name",length = 50)
    @Length(max = 50,message = "")
    private String logTypeName;
    @Column(name = "note",length = 1000)
    @Length(max = 1000,message = "")
    private String note;
}

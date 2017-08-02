package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "dic_user_type_cost_bak20121121")
@Entity
@Setter
@Getter
public class DicUserTypeCostBackUp implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "user_type",length = 10)
    @Length(max = 10,message = "")
    private String userType;
    @Column(name = "cost",length = 30)
    @Length(max = 30,message = "")
    private String cost;
    @Column(name = "cycle",length = 30)
    @Length(max = 30,message = "")
    private String cycle;
}

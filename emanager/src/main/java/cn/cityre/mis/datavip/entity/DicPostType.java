package cn.cityre.mis.datavip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by cityre on 2017/7/27.
 */
@Table(name = "dic_post_type")
@Setter
@Getter
@Entity
public class DicPostType implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true,length = 11)
    private Integer id;
    @Column(name = "post_type_code",length = 10)
    private String typeCode;
    @Column(name = "post_type_name",length = 20)
    private String typeName;
}

package cn.cityre.edi.mis.base.entity.po;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Table(name = "v2017_products",catalog = "cre_mis")
@Entity
@Getter
@Setter
public class MisProductPo implements java.io.Serializable {
    @Id
    @Column(name = "productCode",nullable = false,unique = true,length = 32)
    private String productCode;
    @Column(name = "productName",nullable = false,length = 128)
    private String productName;
    @Column(name = "platform",nullable = false,length = 16)
    private String platform;
    @Column(name = "createTime",nullable = false)
    private Date createTime;
    @Column(name="updateTime",nullable = false)
    private Date updateTime;
}

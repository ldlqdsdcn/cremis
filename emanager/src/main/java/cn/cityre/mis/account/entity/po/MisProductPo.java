package cn.cityre.mis.account.entity.po;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


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
    @Length(min = 2,max = 32,message = "base.v2017.product.code.length")
    private String productCode;
    @Column(name = "productName",nullable = false,length = 128)
    @Length(min = 2 ,max = 128,message = "base.v2017.product.name.length")
    private String productName;
    @Column(name = "platform",nullable = false,length = 16)
    @Length(min = 2,max = 16,message = "base.v2017.product.platform.length")
    private String platform;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name="updateTime")
    private Date updateTime;
}
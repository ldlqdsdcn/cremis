
/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.entity.cpo;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
* table name base_provinces
*            省份信息表
* Date:2017-06-28 15:46:12
**/
@Getter
@Setter
@Entity(name = "province")
public class ProvincePo implements java.io.Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    /**
    * 省份id
    **/
    @Column(name = "[province_code]", length = 20)
    private String provinceid;
    /**
    * 省份名称
    **/
    @Column(name = "[province_name]", length = 50)
    private String province;
    /**
    * 是否有效
    **/
    @Transient
    private String isactive = "Y";
}

/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.entity.po;

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
@Entity(name = "base_provinces")
public class ProvincePo implements java.io.Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    /**
    * 省份id
    **/
    @Column(name = "[provinceid]",length =20 )
    private String provinceid;
    /**
    * 省份名称
    **/
    @Column(name = "[province]",length =50 )
    private String province;
    /**
    * 是否有效
    **/
    @Column(name = "[isactive]",length =1 )
    private String isactive;
}
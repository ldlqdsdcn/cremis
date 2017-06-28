
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
* table name base_cities
*            行政区域地州市信息表
* Date:2017-06-28 15:47:46
**/
@Getter
@Setter
@Entity(name = "base_cities")
public class CityPo implements java.io.Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    /**
    * 城市id
    **/
    @Column(name = "[cityid]",length =20 )
    private String cityid;
    /**
    * 城市名
    **/
    @Column(name = "[city]",length =50 )
    private String city;
    /**
    * 省份
    **/
    @Column(name = "[provinceid]",length =20 )
    private String provinceid;
    /**
    * 是否有效
    **/
    @Column(name = "[isactive]",length =1 )
    private String isactive;
}
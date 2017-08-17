
/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package cn.cityre.edi.mis.base.entity.cpo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
/**
* table name base_cities
*            行政区域地州市信息表
* Date:2017-06-28 15:47:46
**/
@Getter
@Setter
@Entity(name = "City")
public class CityPo implements java.io.Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    /**
    * 城市id
    **/
    @Column(name = "[city_code]", length = 20)
    private String cityid;

    /**
     * 城市id
     **/
    @Column(name = "[city_pinyin]", length = 50)
    private String cityPinYin;
    /**
    * 城市名
    **/
    @Column(name = "[city_name]", length = 50)
    private String city;
    /**
    * 省份
    **/
    @Column(name = "[province_code]", length = 20)
    private String provinceid;
    /**
    * 是否有效
    **/
    @Transient
    private String isactive = "Y";
}
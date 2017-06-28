
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
* table name base_areas
*            行政区域县区信息表
* Date:2017-06-28 15:50:20
**/
@Getter
@Setter
@Entity(name = "base_areas")
public class AreaPo implements java.io.Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    /**
    * 区域id
    **/
    @Column(name = "[areaid]",length =20 )
    private String areaid;
    /**
    * 区域名称
    **/
    @Column(name = "[area]",length =50 )
    private String area;
    /**
    * 城市
    **/
    @Column(name = "[cityid]",length =20 )
    private String cityid;
    /**
    * 是否有效
    **/
    @Column(name = "[isactive]",length =1 )
    private String isactive;
}
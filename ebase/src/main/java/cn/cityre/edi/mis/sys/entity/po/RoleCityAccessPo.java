package cn.cityre.edi.mis.sys.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by 刘大磊 on 2017/6/28 16:46.
 *  角色可以访问的城市
 */
@Getter
@Setter
@Entity(name = "sys_role_cityaccess")
public class RoleCityAccessPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    @Column(name = "role_id" )
    private Integer roleId;
    @Column(name = "city_id")
    private Integer cityId;
}

package cn.cityre.edi.mis.sys.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by 刘大磊 on 2017/6/28 16:45.
 * 用户可以访问的城市
 */
@Getter
@Setter
@Entity(name = "sys_user_cityaccess")
public class UserCityAccessPo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[id]")
    @Id
    private Integer id;
    @Column(name = "user_id" )
    private Integer userId;
    @Column(name = "city_id" )
    private Integer cityId;
}

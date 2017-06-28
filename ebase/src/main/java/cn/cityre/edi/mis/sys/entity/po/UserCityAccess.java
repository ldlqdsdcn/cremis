package cn.cityre.edi.mis.sys.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by 刘大磊 on 2017/6/28 16:45.
 * 用户可以访问的城市
 */
@Getter
@Setter
@Entity(name = "sys_user_cityaccess")
public class UserCityAccess {
    private Integer id;
    private Integer userId;
    private Integer cityId;
}

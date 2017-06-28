package cn.cityre.edi.mis.sys.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by 刘大磊 on 2017/6/28 16:46.
 *  角色可以访问的城市
 */
@Getter
@Setter
@Entity(name = "sys_role_cityaccess")
public class RoleCityAccess {
    private Integer id;
    private Integer roleId;
    private Integer cityId;
}


package com.dsdl.eidea.base.entity.bo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Bobo on 2016/12/17 13:59.
 */
@Getter
@Setter
public class UserBo {
    private Integer id;
    @NotBlank(message = "logon.name.not.empty")
    @Length(min = 1,max = 50,message = "number.length")
    private String username;
    @NotBlank(message = "password.not.allowed.empty")
    @Length(min = 6,max = 45,message = "password.length")
    private String password;
    @NotBlank(message = "pagemenu.name.check")
    @Length(min = 1,max = 45,message = "pagemenu.name.prompt")
    private String name;
    @NotBlank(message = "initial.user.not.allowed.empty")
    @Length(min = 1,max = 1,message = "initial.length")
    private String init;
    @Length(min = 1,max = 100,message = "mailbox.length")
    private String email;
    @Length(min = 1,max = 45,message = "telephone.number.length")
    private String telephone;
    @Length(min = 1,max = 1,message = "isactive.length")
    private String isactive;
    private String userPic;
    private Integer clientId;
    private Integer orgId;
    private Integer[] roleIds;
    private boolean created=false;
    private String code;
}

package com.dsdl.eidea.base.web.vo;

import cn.cityre.edi.mis.sys.entity.bo.LetterBo;
import com.dsdl.eidea.base.entity.bo.UserBo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/6/29 11:26.
 */
@Getter
@Setter
public class UserAccessCitiesVo {
    /**
     * 用户信息
     */
    Integer userId;
    /**
     * 用户登陆名
     */
    String username;
    /**
     * 用户名
     */
    String name;
    /**
     * 所属集团
     */
    private String clientName;
    /**
     * 所属组织
     */
    private String orgName;
    /**
     * 城市省份选择列表
     */
    private List<LetterBo> letterBoList;
}

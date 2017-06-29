package com.dsdl.eidea.base.web.vo;

import cn.cityre.edi.mis.sys.entity.bo.LetterBo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by 刘大磊 on 2017/6/29 11:26.
 */
@Getter
@Setter
public class RoleAccessCitiesVo {
    /**
     * 角色信息
     */
    Integer roleId;
    /**
     * 角色名
     */
    String name;
    /**
     * 城市省份选择列表
     */
    private List<LetterBo> letterBoList;
}

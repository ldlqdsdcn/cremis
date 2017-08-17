package com.dsdl.eidea.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Angularjs 分页前端设置类
 * Created by 刘大磊 on 2016/10/20 17:41.
 */
@Getter
@Setter
public class EasyUiForm implements java.io.Serializable{
    /**
     * 当前页
     */
    private Integer limit;
    /**
     * 记录数
     */
    private Integer start;

    /**
     * 记录数
     */
    private Integer rows;

    /**
     * 记录数
     */
    private Integer page;
}

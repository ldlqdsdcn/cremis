package cn.cityre.mis.city.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class Perdaycount implements java.io.Serializable {
    private Date ym;
    private Integer qnt;
}

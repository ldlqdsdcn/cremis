package cn.cityre.mis.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class CoCheck implements java.io.Serializable {
    private Integer id;
    private String certPhoto;
    private String coName;
    private String coCode;
    private String certification;
    private String artiPsn;//法人代表
    private String coimagefile;
    private String coimagefile_flag;
    private Date certPhoto_uploadtime;
    private String cocert_updateuid;
    private Date cocert_updatetime;
    private Integer costate;

    private String couid;

}

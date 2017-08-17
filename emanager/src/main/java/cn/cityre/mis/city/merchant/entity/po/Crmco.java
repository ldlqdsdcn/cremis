package cn.cityre.mis.city.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class Crmco implements java.io.Serializable {
  private Integer id;
  private String  tel_cocert_state;
  private Date tel_cocert_updatetime;
  private Date cocert_updatetime;
  private Date tel_cocert_times;
  private String costate_uid;
  private Date costate_time;
  private String coimagefile;
  private String cocode;
  private String coname;
  private String coaddr;
  private String artiPsn;
  private Date updatetime;
  private String couid;
  private String coScale;
  private String coTel;
  private String costate;
  private String x;
  private String y;
  private Date regTime;
  private String verifyuid;
  private Date verifytime;
  private String certNo;
  private String certPhoto;
  private String coEmail;
  private String coinfo;
  private String certification;
  private String cnt;//房源数量
  private String uid;
  private String permission;
  private String name;
  private String nickname;


  private Integer couserCnt;
  private String cobrief;
  private String cowebsite;
  private String cofax;
  private String coMob;

  private String cocl;//店铺类型
}

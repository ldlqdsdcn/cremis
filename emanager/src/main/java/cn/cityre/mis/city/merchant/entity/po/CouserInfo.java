package cn.cityre.mis.city.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class CouserInfo implements java.io.Serializable {
  private Integer id;
  private String user_type;
  private String tel_cert_state;
  private String tuijian_uid;
  private String tel_cert_times;
  private String tel_cert_updatetime;
  private String usercert_updatetime;
  private String sex;
  private String headportrait_flag;
  private String userconame;
  private String usercoaddr;
  private String certPhoto;
  private String headportrait;
  private String coname;
  private String cocode;
  private String certification;
  private String uid;//userlist
  private String iDNo;
  private String coaddr;
  private String costate;
  private Integer isadmin;
  private String userlist_flag;
  private String userlist_flag_uid;
  private String userlist_flag_time;
  private Date updatetime;//userlist
  private String name;
  private String nickName;
  private Date applytime;
  private String setuid;
  private String logintm;
  private Integer cnt;//房源量
  private String adminstr;
  private String address;
  private String tel;
  private String email;
  private String adapplay;//小区中介申请
  private String userlist_remark;
}

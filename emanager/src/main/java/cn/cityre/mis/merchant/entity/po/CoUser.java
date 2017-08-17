package cn.cityre.mis.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class CoUser implements java.io.Serializable {
  private Integer id;
  private String uid;
  private String name;
  private String idno;
  private String headportrait;
  private Integer headportrait_flag;
  private String certPhoto;
  private String certification;
  private String usercert_updateuid;
  private Date usercert_updatetime;
  private Date certphoto_uploadtime;
  private String cocode;
  private Integer isadmin;
}

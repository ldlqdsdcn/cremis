package cn.cityre.mis.merchant.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by cityre on 2017/7/11.
 */
@Getter
@Setter
public class UserList implements java.io.Serializable {
  private Integer id;
  private String uid;
  private String name;
  private String coCode;
  private String nickname;
  private String sex;
  private Date bornDate;
  private String idcl;
  private String idno;
  private String telno;
  private String email;
  private Date regtime;
  private String companyName;
  private Integer adid;//判断是否有服务小区（包括正在服务和过期的）
  private String coUID;//判断是否是否管理员
  private List<ServiceHa> serviceingHa; //正在服务的小区
  private List<ServiceHa> expiredServiceHa;//过期服务的小区
  private String setuid;
  private String headportrait;
  private String coUrl;
  private Integer userlist_flag;

  private String permission;

  private Integer sourceCnt;//房源数量

}

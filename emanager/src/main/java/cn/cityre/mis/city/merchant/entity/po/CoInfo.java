package cn.cityre.mis.city.merchant.entity.po;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */

public class CoInfo implements java.io.Serializable {
  private Integer id;
  private String coname;
  private String coAddr;
  private String cofax;
  private String cotel;
  private String coemail;
  private String cowebsite;
  private Date regtime;
  private Date updatetime;
  private String slogan;
  private String couid;
  private String coimagefile;
  private String coinfo;

  public String getCocode() {
    return cocode;
  }

  public void setCocode(String cocode) {
    this.cocode = cocode;
  }

  private String cocode;
  private String regtimestr;
  private String updatetimestr;

  private Integer sourceCnt;

  public Integer getSourceCnt() {
    return sourceCnt;
  }

  public void setSourceCnt(Integer sourceCnt) {
    this.sourceCnt = sourceCnt;
  }
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getConame() {
    return coname;
  }

  public void setConame(String coname) {
    this.coname = coname;
  }

  public String getCoAddr() {
    return coAddr;
  }

  public void setCoAddr(String coAddr) {
    this.coAddr = coAddr;
  }

  public String getCofax() {
    return cofax;
  }

  public void setCofax(String cofax) {
    this.cofax = cofax;
  }

  public String getCotel() {
    return cotel;
  }

  public void setCotel(String cotel) {
    this.cotel = cotel;
  }

  public String getCoemail() {
    return coemail;
  }

  public void setCoemail(String coemail) {
    this.coemail = coemail;
  }

  public String getCowebsite() {
    return cowebsite;
  }

  public void setCowebsite(String cowebsite) {
    this.cowebsite = cowebsite;
  }

  public Date getRegtime() {
    return regtime;
  }

  public void setRegtime(Date regtime) {
    this.regtime = regtime;
  }

  public Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
    this.updatetime = updatetime;
  }

  public String getSlogan() {
    return slogan;
  }

  public void setSlogan(String slogan) {
    this.slogan = slogan;
  }

  public String getCouid() {
    return couid;
  }

  public void setCouid(String couid) {
    this.couid = couid;
  }

  public String getCoimagefile() {
    return coimagefile;
  }

  public void setCoimagefile(String coimagefile) {
    this.coimagefile = coimagefile;
  }

  public String getCoinfo() {
    return coinfo;
  }

  public void setCoinfo(String coinfo) {
    this.coinfo = coinfo;
  }

  public String getRegtimestr() {
    if(this.regtime != null){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      regtimestr = sdf.format(this.regtime);
    }
    return regtimestr;
  }

  public void setRegtimestr(String regtimestr) {

    this.regtimestr = regtimestr;
  }

  public String getUpdatetimestr() {
    if(this.updatetime != null){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      updatetimestr = sdf.format(this.updatetime);
    }
    return updatetimestr;
  }

  public void setUpdatetimestr(String updatetimestr) {

    this.updatetimestr = updatetimestr;
  }


}

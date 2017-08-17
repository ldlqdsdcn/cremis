package cn.cityre.mis.merchant.entity.po;

import java.util.Date;

/**
 * Created by cityre on 2017/7/11.
 */

public class ServiceHa implements java.io.Serializable {
  private String haName;
  private String haType;
  private Date startDate;
  private Date endDate;
  public String getHaName() {
    return haName;
  }

  public void setHaName(String haName) {
    this.haName = haName;
  }

  public String getHaType() {
    return haType;
  }

  public void setHaType(String haType) {
    this.haType = haType;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}

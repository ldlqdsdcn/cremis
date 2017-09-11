package cn.cityre.mis.cityre_center.model;

import java.util.Date;

public class City {
    private String cityCode;

    private Integer id;

    private String cityPinyin;

    private String provinceCode;

    private String province;

    private String cityName;

    private String cityUrl;

    private Integer ordernum;

    private String cityOrder;

    private String gbcode;

    private Integer iscapital;

    private Integer isdirectcity;

    private Integer level;

    private String adduid;

    private Date addtime;

    private String updateuid;

    private Date updatetime;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin == null ? null : cityPinyin.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl == null ? null : cityUrl.trim();
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getCityOrder() {
        return cityOrder;
    }

    public void setCityOrder(String cityOrder) {
        this.cityOrder = cityOrder == null ? null : cityOrder.trim();
    }

    public String getGbcode() {
        return gbcode;
    }

    public void setGbcode(String gbcode) {
        this.gbcode = gbcode == null ? null : gbcode.trim();
    }

    public Integer getIscapital() {
        return iscapital;
    }

    public void setIscapital(Integer iscapital) {
        this.iscapital = iscapital;
    }

    public Integer getIsdirectcity() {
        return isdirectcity;
    }

    public void setIsdirectcity(Integer isdirectcity) {
        this.isdirectcity = isdirectcity;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAdduid() {
        return adduid;
    }

    public void setAdduid(String adduid) {
        this.adduid = adduid == null ? null : adduid.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getUpdateuid() {
        return updateuid;
    }

    public void setUpdateuid(String updateuid) {
        this.updateuid = updateuid == null ? null : updateuid.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
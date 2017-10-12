package cn.cityre.mis.sys.model;

import java.util.Date;

public class UserCitySelected {
    private Integer id;

    private String cityCode;

    private String unionuid;

    private Date created;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getUnionuid() {
        return unionuid;
    }

    public void setUnionuid(String unionuid) {
        this.unionuid = unionuid == null ? null : unionuid.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
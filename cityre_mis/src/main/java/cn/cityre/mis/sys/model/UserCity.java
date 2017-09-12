package cn.cityre.mis.sys.model;

import java.util.Date;

public class UserCity {
    private Integer id;

    private String unionUid;

    private String cityCode;

    private Date created;

    private String createdby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid == null ? null : unionUid.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }
}
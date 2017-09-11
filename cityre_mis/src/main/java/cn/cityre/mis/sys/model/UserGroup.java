package cn.cityre.mis.sys.model;

import java.util.Date;

public class UserGroup {
    private Integer id;

    private String unionuid;

    private Integer groupId;

    private Date created;

    private String createdby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnionuid() {
        return unionuid;
    }

    public void setUnionuid(String unionuid) {
        this.unionuid = unionuid == null ? null : unionuid.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
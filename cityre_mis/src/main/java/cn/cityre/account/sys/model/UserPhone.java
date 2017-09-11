package cn.cityre.account.sys.model;

import java.util.Date;

public class UserPhone {
    private Integer id;

    private String unionuid;

    private String phone;

    private Byte isverified;

    private Byte isprimary;

    private Date createtime;

    private Date updatetime;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getIsverified() {
        return isverified;
    }

    public void setIsverified(Byte isverified) {
        this.isverified = isverified;
    }

    public Byte getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(Byte isprimary) {
        this.isprimary = isprimary;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
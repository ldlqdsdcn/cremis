package cn.cityre.account.sys.model;

import java.util.Date;

public class UserEmail {
    private Integer id;

    private String unionuid;

    private String address;

    private Byte isverified;

    private Byte isprimary;

    private String pintoken;

    private Date expiretime;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getPintoken() {
        return pintoken;
    }

    public void setPintoken(String pintoken) {
        this.pintoken = pintoken == null ? null : pintoken.trim();
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
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
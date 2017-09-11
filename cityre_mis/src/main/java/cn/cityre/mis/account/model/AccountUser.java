package cn.cityre.mis.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AccountUser {
    private Integer id;

    private String unionuid;

    private String userid;

    private String nickname;

    private String realname;

    private Byte sex;

    private Date birthday;

    private String usericon;

    private String passwordsalt;

    private String passwordhash;

    private String oldpasswordhash;

    private Byte isverified;

    private Byte isvalid;

    private String accountsecret;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon == null ? null : usericon.trim();
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt == null ? null : passwordsalt.trim();
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash == null ? null : passwordhash.trim();
    }

    public String getOldpasswordhash() {
        return oldpasswordhash;
    }

    public void setOldpasswordhash(String oldpasswordhash) {
        this.oldpasswordhash = oldpasswordhash == null ? null : oldpasswordhash.trim();
    }

    public Byte getIsverified() {
        return isverified;
    }

    public void setIsverified(Byte isverified) {
        this.isverified = isverified;
    }

    public Byte getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    public String getAccountsecret() {
        return accountsecret;
    }

    public void setAccountsecret(String accountsecret) {
        this.accountsecret = accountsecret == null ? null : accountsecret.trim();
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
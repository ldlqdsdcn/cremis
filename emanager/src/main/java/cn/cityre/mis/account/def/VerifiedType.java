package cn.cityre.mis.account.def;

/**
 * Created by cityre on 2017/7/13.
 * 是否已验证 0-没有 1-Email 2-SMS 3-Email和SMS都验证
 */
public enum VerifiedType {
NO(0,"没有验证"),EMAIL(1,"Email已验证"),SMS(2,"SMS已验证"),ALL(3,"Email和SMS都已验证")    ;

    private Integer key;
    private String desc;

    VerifiedType(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

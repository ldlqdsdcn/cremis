package cn.cityre.edi.mis.base.def;

/**
 * Created by cityre on 2017/7/13.
 * 性别 1-男 2-女
 */
public enum SexType {
    MAN(1, "男"), WOMEN(2, "女");


    private Integer key;
    private String desc;

    SexType(Integer key, String desc) {
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

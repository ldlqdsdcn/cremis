package cn.cityre.mis.datavip.del;

/**
 * Created by cityre on 2017/8/16.
 */
public enum InvoiceFlag {
    YES(1,"是"),NO(0,"否");
    private Integer key;
    private String value;

    InvoiceFlag(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

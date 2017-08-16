package cn.cityre.mis.datavip.del;

/**
 * Created by cityre on 2017/8/15.
 */
public enum InvoiceType {
    PERSONAL(1,"个人"),COMPANY(2,"单位"),NULL(null,null);
    private Integer key;
    private String value;

    InvoiceType(Integer key, String value) {
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

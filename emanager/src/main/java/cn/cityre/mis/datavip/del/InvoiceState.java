package cn.cityre.mis.datavip.del;

/**
 * Created by cityre on 2017/8/16.
 */
public enum InvoiceState {
    KINVOICE(1,"已开票"),NINVOICE(0,"未开票");
    private Integer key;
    private String value;

    InvoiceState(Integer key, String value) {
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

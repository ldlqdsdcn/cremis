package cn.cityre.mis.datavip.del;

/**
 * Created by cityre on 2017/8/16.
 */
public enum BillFlagDef {

    PAY(1,"已支付"),UNPAY(0,"已申请"),NOWPAY(2,"已进入支付页");

    private Integer key;
    private String value;

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

    BillFlagDef(Integer key, String value) {

        this.key = key;
        this.value = value;
    }
}

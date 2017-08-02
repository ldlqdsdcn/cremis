package cn.cityre.mis.datavip.del;

/**
 * Created by cityre on 2017/7/27.
 *
 * 楼盘的租售类型
 */
public enum DealType {
    SALE(1,"出售"),RENT(2,"出租"),NEW(3,"新楼盘"),START(4,"新楼盘开工在售"),
    END(5,"新楼盘已竣工"),NOSALE(6,"新楼盘未售");

    DealType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

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
}

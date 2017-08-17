package cn.cityre.mis.datavip.del;

/**
 * 区域的级别
 * Created by cityre on 2017/8/4.
 */
public enum AreaLevel {
    VILLAGE(1, "小区"), DISTRICT(2, "行政区"), COUNTRY(3, "城市"), VICINOTY(4, "附近");

    AreaLevel(Integer key, String value) {
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

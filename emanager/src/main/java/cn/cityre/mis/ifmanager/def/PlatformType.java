package cn.cityre.mis.ifmanager.def;

/**
 * Created by cityre on 2017/7/13.
 */
public enum PlatformType {
//    产品平台 ios android web mobile weixin
    IOS("ios","苹果公司产品"),ANDROID("android","安卓平台"),WEB("web","网站"),MOBILE("mobile","其他手机平台"),WEIXIN("weixin","微信");

    PlatformType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

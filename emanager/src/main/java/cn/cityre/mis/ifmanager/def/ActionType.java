package cn.cityre.mis.ifmanager.def;

/**
 * Created by cityre on 2017/7/13.
 */
public enum ActionType {
//    操作 regist - 用户注册  login - 用户登录 pwdreset - 密码重置  verify - 电话验证
    REGIST("register","注册"),LOGIN("login","用户登录"),PEDRESESET("pwdreset","密码重置"),
    VERIFY("verify","电话验证");
    ActionType(String key, String desc){
        this.key=key;
        this.desc=desc;
    }
    private String key;
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

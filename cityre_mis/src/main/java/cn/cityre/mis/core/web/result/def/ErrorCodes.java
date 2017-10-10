package cn.cityre.mis.core.web.result.def;


/**
 * Created by admin on 2016/9/6.
 * 错误状态码
 */
public enum ErrorCodes {
    BUSINESS_EXCEPTION(1, "业务逻辑异常"), NO_PRIVILEGES(2, "没有操作权限"), VALIDATE_PARAM_ERROR(3, "参数验证错误"), NO_LOGIN(4, "你需要登录");
    private final int code;
    private final String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

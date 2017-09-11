package cn.cityre.mis.core.web.result;


import cn.cityre.mis.core.web.result.def.ResultCode;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by admin on 2016/8/23.
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private int code;
    private String message;
    private String requestId;
    private int errorCode;
    private T data;

    private JsonResult() {
        this.requestId = UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.success = true;
        jsonResult.code = ResultCode.SUCCESS.getCode();
        jsonResult.message = ResultCode.SUCCESS.getMessage();
        jsonResult.data = data;
        return jsonResult;
    }

    public static <T> JsonResult<T> fail(int errorCode, String message) {
        JsonResult<T> apiResult = new JsonResult<>();
        apiResult.success = false;
        apiResult.code = ResultCode.FAILURE.getCode();
        apiResult.message = message;
        apiResult.errorCode = errorCode;
        return apiResult;
    }

    public static <T> JsonResult<T> fail(int errorCode, T errorMessage) {
        JsonResult<T> apiResult = new JsonResult<>();
        apiResult.success = false;
        apiResult.code = ResultCode.FAILURE.getCode();
        apiResult.message = ResultCode.FAILURE.getMessage();
        apiResult.data = errorMessage;
        apiResult.errorCode = errorCode;
        return apiResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }
}

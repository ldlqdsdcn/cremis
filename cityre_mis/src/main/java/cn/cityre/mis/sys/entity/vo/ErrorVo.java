package cn.cityre.mis.sys.entity.vo;

/**
 * Created by 刘大磊 on 2017/8/31 10:03.
 */
public class ErrorVo {
    private int code;
    private String message;
    private String detail;
    private String detailInfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }
}

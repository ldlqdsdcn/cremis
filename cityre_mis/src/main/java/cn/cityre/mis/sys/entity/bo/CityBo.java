package cn.cityre.mis.sys.entity.bo;

/**
 * Created by 刘大磊 on 2017/9/12 15:58.
 * 城市信息
 */
public class CityBo {
    private String code;

    private String name;

    private String provinceCode;

    private boolean checked;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}

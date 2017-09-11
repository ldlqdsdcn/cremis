package cn.cityre.mis.city.model;

public class District {
    private Integer id;

    private String distName;

    private String distCode;

    private Integer distid;

    private Integer urbanId;

    private String gbcode;

    private String distspell;

    private String distIntroduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName == null ? null : distName.trim();
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode == null ? null : distCode.trim();
    }

    public Integer getDistid() {
        return distid;
    }

    public void setDistid(Integer distid) {
        this.distid = distid;
    }

    public Integer getUrbanId() {
        return urbanId;
    }

    public void setUrbanId(Integer urbanId) {
        this.urbanId = urbanId;
    }

    public String getGbcode() {
        return gbcode;
    }

    public void setGbcode(String gbcode) {
        this.gbcode = gbcode == null ? null : gbcode.trim();
    }

    public String getDistspell() {
        return distspell;
    }

    public void setDistspell(String distspell) {
        this.distspell = distspell == null ? null : distspell.trim();
    }

    public String getDistIntroduction() {
        return distIntroduction;
    }

    public void setDistIntroduction(String distIntroduction) {
        this.distIntroduction = distIntroduction == null ? null : distIntroduction.trim();
    }
}
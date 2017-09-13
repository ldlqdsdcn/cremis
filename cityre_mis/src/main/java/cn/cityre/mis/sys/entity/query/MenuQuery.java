package cn.cityre.mis.sys.entity.query;

import java.util.Set;

/**
 * Created by 刘大磊 on 2017/9/5 15:58.
 */
public class MenuQuery {
    private String nameLike;
    private String parentNameLike;
    private String isactive;
    private Integer type;
    private Set<Integer> repIds;
    private Set<Integer> repInIds;

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getParentNameLike() {
        return parentNameLike;
    }

    public void setParentNameLike(String parentNameLike) {
        this.parentNameLike = parentNameLike;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Set<Integer> getRepIds() {
        return repIds;
    }

    public void setRepIds(Set<Integer> repIds) {
        this.repIds = repIds;
    }

    public Set<Integer> getRepInIds() {
        return repInIds;
    }

    public void setRepInIds(Set<Integer> repInIds) {
        this.repInIds = repInIds;
    }
}

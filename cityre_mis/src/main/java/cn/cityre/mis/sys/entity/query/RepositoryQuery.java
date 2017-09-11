package cn.cityre.mis.sys.entity.query;

import cn.cityre.mis.core.entity.query.BaseQuery;

/**
 * Created by 刘大磊 on 2017/8/28 17:53.
 */
public class RepositoryQuery extends BaseQuery {
    private String nameLike;
    private String noLike;
    private String isactive;
    public String getNameLike() {
        return nameLike;
    }
    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }
    public String getNoLike() {
        return noLike;
    }
    public void setNoLike(String noLike) {
        this.noLike = noLike;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
}

package cn.cityre.mis.account.entity.union;

import java.util.Date;

/**
 * Created by 刘大磊 on 2017/9/4 15:08.
 */
public class UserRepositoryUnion {
    private Integer id;

    private String unionUid;

    private Integer repositoryId;

    private String repositoryNo;

    private String repositoryName;

    private Date created;

    private String createdby;

    private boolean checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnionUid() {
        return unionUid;
    }

    public void setUnionUid(String unionUid) {
        this.unionUid = unionUid;
    }

    public Integer getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getRepositoryNo() {
        return repositoryNo;
    }

    public void setRepositoryNo(String repositoryNo) {
        this.repositoryNo = repositoryNo;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

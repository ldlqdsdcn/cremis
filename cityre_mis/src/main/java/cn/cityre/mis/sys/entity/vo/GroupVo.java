package cn.cityre.mis.sys.entity.vo;

import cn.cityre.mis.sys.entity.union.GroupRepositoryUnion;
import cn.cityre.mis.sys.model.Group;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 刘大磊 on 2017/9/1 15:07.
 */
public class GroupVo {
    @Valid
    private Group group;

    private List<GroupRepositoryUnion> groupRepositoryUnionList;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<GroupRepositoryUnion> getGroupRepositoryUnionList() {
        return groupRepositoryUnionList;
    }

    public void setGroupRepositoryUnionList(List<GroupRepositoryUnion> groupRepositoryUnionList) {
        this.groupRepositoryUnionList = groupRepositoryUnionList;
    }
}

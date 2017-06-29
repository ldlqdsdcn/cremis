package com.dsdl.eidea.base.service;

import java.util.List;

import cn.cityre.edi.mis.sys.entity.bo.LetterBo;
import com.dsdl.eidea.base.entity.bo.RoleBo;
import com.dsdl.eidea.base.entity.po.RolePo;
import com.dsdl.eidea.core.dto.PaginationResult;
import com.dsdl.eidea.core.params.QueryParams;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;

public interface RoleService {
    void save(RoleBo roleBo);

    boolean findExistRole(String no);

    RoleBo findExistRoleByName(String roleName);

    RoleBo getRoleBo(Integer id);

    PaginationResult<RoleBo> getRoleList(Search search, QueryParams queryParams);

    void deletes(Integer[] ids);

    RoleBo getInitRoleBo(RoleBo roleBo);

    boolean getHasUsers(Integer id);


    /**
     * 获取用户可访问的省份列表
     * @param roleId
     * @return
     */
    List<LetterBo> getProvinceAccessList(Integer roleId);

    /**
     * 保存用户访问城市权限
     * @param roleId 当前选择的角色id
     * @param letterBoList 当前选择的权限列表
     */
    void saveRoleAccessCitiesPrivileges(Integer roleId,List<LetterBo> letterBoList);
}

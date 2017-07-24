package com.dsdl.eidea.base.entity.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * SysRoleOrgaccess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_orgaccess", catalog = "cityre_mis")
@Getter
@Setter
public class RoleOrgaccessPo implements java.io.Serializable {

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sys_org_id", nullable = false)
	private OrgPo sysOrg;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sys_role_id", nullable = false)
	private RolePo sysRole;
}
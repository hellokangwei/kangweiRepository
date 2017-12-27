package com.smbms.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * Role用户角色
 * @author yy
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;//id
	private String roleCode;//角色编号
	private String roleName;//角色名称
	private int createdBy;//创建
	private Date creationDate;//创建时间
	private int modifyBy;//修改
	private Date modifyDate;//修改时间
	
	//数据封装
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}

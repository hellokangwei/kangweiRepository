package com.smbms.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 供应商
 * @author YangMr
 *
 */
public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;
    
	private Integer id; //主键id
	private String proCode;//供应商编码
    private String proName;//供应商名称
    private String proDesc;//供应商详细描述
    private String proContact;//供应商联系人
    private String proPhone;//供应商联系电话
    private String proAddress;//地址
    private String proFax;//传真
    private Integer createdBy;//创建者
    private Date creationDate;//创建时间
    private Date modifyDate;//修改者
    private Integer modifyBy;//修改时间
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getproCode() {
		return proCode;
	}
	public void setproCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getproDesc() {
		return proDesc;
	}
	public void setproDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProContact() {
		return proContact;
	}
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	public String getProPhone() {
		return proPhone;
	}
	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}
	public String getProAddress() {
		return proAddress;
	}
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	public String getProFax() {
		return proFax;
	}
	public void setProFax(String proFax) {
		this.proFax = proFax;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	
}

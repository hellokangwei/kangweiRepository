package com.smbms.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * ��Ӧ��
 * @author YangMr
 *
 */
public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;
    
	private Integer id; //����id
	private String proCode;//��Ӧ�̱���
    private String proName;//��Ӧ������
    private String proDesc;//��Ӧ����ϸ����
    private String proContact;//��Ӧ����ϵ��
    private String proPhone;//��Ӧ����ϵ�绰
    private String proAddress;//��ַ
    private String proFax;//����
    private Integer createdBy;//������
    private Date creationDate;//����ʱ��
    private Date modifyDate;//�޸���
    private Integer modifyBy;//�޸�ʱ��
    
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

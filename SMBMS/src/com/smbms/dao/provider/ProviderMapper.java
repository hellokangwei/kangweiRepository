package com.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smbms.pojo.Provider;
import com.smbms.tool.PageUtil;

@Repository
public interface ProviderMapper {
	
	/**
	 * ��ӹ�Ӧ����Ϣ
	 * @param provider ��Ӧ�̶���
	 */
	public int addProvider(Provider provider);
	
	/**
	 * ���ù�Ӧ���Ƿ����
	 * @param id
	 * @return
	 */
	public int checkProviderIsExist(@Param("id") Integer id);
	
	/**
	 * ���ù�Ӧ���Ƿ���ڶ��� ����������ɾ����Ӧ��
	 * @param id ��Ӧ��id
	 * @return ��Ӱ������
	 */
	public int checkBillIsExist(@Param("id") Integer id);
	
	/**
	 * ����idɾ��ɾ����Ӧ����Ϣ
	 * @param id ����id
	 */
	public int deleteProvider(@Param("id")Integer id);
	
	/**
	 * �޸��û���Ϣ
	 * @param provider �û�����
	 * @return �޸ĳɹ����ش���0����Ӱ������
	 */
	public int updateProvider(@Param("provider") Provider provider);
	
	/**
	 * �鿴��Ӧ����ͼ
	 * @param id ����id
	 * @return ��Ӧ�̶���
	 */
	public Provider searchProView(Integer id);
	
	/**
	 * ���ݱ�������ƻ�ȡ��Ӧ��������
	 * @param proCode ��Ӧ�̱���
	 * @param proName ��Ӧ������
	 */
	public int getProviderCount(@Param("proCode")String proCode,@Param("proName")String proName);
	
	/**
	 * ���ݱ�������ƻ�ȡ��Ӧ����Ϣ
	 * @param proCode ��Ӧ�̱���
	 * @param proName ��Ӧ������
	 */
	public List<Provider> getProviderList(@Param("page")PageUtil<Provider> page ,@Param("proCode")String proCode,@Param("proName")String proName);
	
}

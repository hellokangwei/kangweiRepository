package com.smbms.biz.provider;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.smbms.dao.provider.ProviderMapper;
import com.smbms.pojo.Provider;
import com.smbms.tool.PageUtil;
/**
 * ��Ӧ��ҵ���߼���
 * @author YangMr
 */
@Service("providerBiz")
public class ProviderBiz {
	@Resource
	private ProviderMapper providerMapper;
	
	/**
	 * ��ӹ�Ӧ����Ϣ
	 * @param provider ��Ӧ�̶���
	 */
	public boolean addProvider(Provider provider) {
		return providerMapper.addProvider(provider)>0 ? true:false;
	}
	/**
	 * ���ù�Ӧ���Ƿ����
	 * @param id
	 * @return
	 */
	public boolean checkProviderIsExist(Integer id) {
		return providerMapper.checkProviderIsExist(id) >0? true:false;
	}
	
	/**
	 * ���ù�Ӧ���Ƿ���ڶ�������������ɾ����Ӧ��
	 * @param id ��Ӧ��id
	 * @return ��Ӱ������
	 */
	public int checkBillIsExist(@Param("id") Integer id) {
		return providerMapper.checkBillIsExist(id);
	}
	
	/**
	 * ����idɾ��ɾ����Ӧ����Ϣ
	 * @param id ����id
	 */
	public boolean deleteProvider(Integer id) {
		return providerMapper.deleteProvider(id)>0 ? true:false;
	}
	
	/**
	 * �޸��û���Ϣ
	 * @param provider �û�����
	 * @return �޸ĳɹ����ش���0����Ӱ������
	 */
	public boolean updateProvider(Provider provider) {
		return providerMapper.updateProvider(provider) > 0? true:false;
	}
	/**
	 * �鿴��Ӧ����ͼ
	 * @param id ����id
	 * @return ��Ӧ�̶���
	 */
	public Provider searchProView(Integer id) {
		return providerMapper.searchProView(id);
	}
	
	/**
	 * ���ݱ�������ƻ�ȡ��Ӧ��������
	 * @param proCode ��Ӧ�̱���
	 * @param proName ��Ӧ������
	 */
	public int getProviderCount(String proCode,String proName) {
		return providerMapper.getProviderCount(proCode, proName);
	}
	/**
	 * ���ݱ�������ƻ�ȡ��Ӧ����Ϣ
	 * @param proCode ��Ӧ�̱���
	 * @param proName ��Ӧ������
	 */
	public List<Provider> getProviderList(PageUtil<Provider> proPage,String proCode,String proName) {
		proPage.setNum((proPage.getPageIndex()-1) * proPage.getPageSize());
		return providerMapper.getProviderList(proPage,proCode, proName);
	}
	
}

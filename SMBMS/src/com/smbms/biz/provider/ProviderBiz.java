package com.smbms.biz.provider;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.smbms.dao.provider.ProviderMapper;
import com.smbms.pojo.Provider;
import com.smbms.tool.PageUtil;
/**
 * 供应商业务逻辑层
 * @author YangMr
 */
@Service("providerBiz")
public class ProviderBiz {
	@Resource
	private ProviderMapper providerMapper;
	
	/**
	 * 添加供应商信息
	 * @param provider 供应商对象
	 */
	public boolean addProvider(Provider provider) {
		return providerMapper.addProvider(provider)>0 ? true:false;
	}
	/**
	 * 检查该供应商是否存在
	 * @param id
	 * @return
	 */
	public boolean checkProviderIsExist(Integer id) {
		return providerMapper.checkProviderIsExist(id) >0? true:false;
	}
	
	/**
	 * 检查该供应商是否存在订单，存在则不能删除供应商
	 * @param id 供应商id
	 * @return 受影响行数
	 */
	public int checkBillIsExist(@Param("id") Integer id) {
		return providerMapper.checkBillIsExist(id);
	}
	
	/**
	 * 根据id删除删除供应商信息
	 * @param id 主键id
	 */
	public boolean deleteProvider(Integer id) {
		return providerMapper.deleteProvider(id)>0 ? true:false;
	}
	
	/**
	 * 修改用户信息
	 * @param provider 用户对象
	 * @return 修改成功返回大于0的受影响行数
	 */
	public boolean updateProvider(Provider provider) {
		return providerMapper.updateProvider(provider) > 0? true:false;
	}
	/**
	 * 查看供应商视图
	 * @param id 主键id
	 * @return 供应商对象
	 */
	public Provider searchProView(Integer id) {
		return providerMapper.searchProView(id);
	}
	
	/**
	 * 根据编码和名称获取供应商总条数
	 * @param proCode 供应商编码
	 * @param proName 供应商名称
	 */
	public int getProviderCount(String proCode,String proName) {
		return providerMapper.getProviderCount(proCode, proName);
	}
	/**
	 * 根据编码和名称获取供应商信息
	 * @param proCode 供应商编码
	 * @param proName 供应商名称
	 */
	public List<Provider> getProviderList(PageUtil<Provider> proPage,String proCode,String proName) {
		proPage.setNum((proPage.getPageIndex()-1) * proPage.getPageSize());
		return providerMapper.getProviderList(proPage,proCode, proName);
	}
	
}

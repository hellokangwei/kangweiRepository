package com.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smbms.pojo.Provider;
import com.smbms.tool.PageUtil;

@Repository
public interface ProviderMapper {
	
	/**
	 * 添加供应商信息
	 * @param provider 供应商对象
	 */
	public int addProvider(Provider provider);
	
	/**
	 * 检查该供应商是否存在
	 * @param id
	 * @return
	 */
	public int checkProviderIsExist(@Param("id") Integer id);
	
	/**
	 * 检查该供应商是否存在订单 ，存在则不能删除供应商
	 * @param id 供应商id
	 * @return 受影响行数
	 */
	public int checkBillIsExist(@Param("id") Integer id);
	
	/**
	 * 根据id删除删除供应商信息
	 * @param id 主键id
	 */
	public int deleteProvider(@Param("id")Integer id);
	
	/**
	 * 修改用户信息
	 * @param provider 用户对象
	 * @return 修改成功返回大于0的受影响行数
	 */
	public int updateProvider(@Param("provider") Provider provider);
	
	/**
	 * 查看供应商视图
	 * @param id 主键id
	 * @return 供应商对象
	 */
	public Provider searchProView(Integer id);
	
	/**
	 * 根据编码和名称获取供应商总条数
	 * @param proCode 供应商编码
	 * @param proName 供应商名称
	 */
	public int getProviderCount(@Param("proCode")String proCode,@Param("proName")String proName);
	
	/**
	 * 根据编码和名称获取供应商信息
	 * @param proCode 供应商编码
	 * @param proName 供应商名称
	 */
	public List<Provider> getProviderList(@Param("page")PageUtil<Provider> page ,@Param("proCode")String proCode,@Param("proName")String proName);
	
}

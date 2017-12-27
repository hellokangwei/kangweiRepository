package com.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.tool.PageUtil;

@Repository
public interface BillMapper {
	/**
	 * 修改订单信息
	 * @param bill 订单对象
	 * @param providerId 供应商id
	 */
	public int updateBill(@Param("bill") Bill bill,@Param("providerId")Integer providerId);
	
	/**
	 * 添加订单
	 */
	public int addbill(@Param("bill") Bill bill,@Param("providerId")Integer providerId);
	
	/**
	 * 根据订单id查询订单是否存在
	 * @param id 订单id
	 * @return
	 */
	public int checkBillById(Integer id);
	
	/**
	 * 根据订单id删除订单
	 * @param id 订单id
	 * @return
	 */
	public int deleteBillById(Integer id);
	
	/**
	 * 根据订单id获取订单信息
	 * @param id 订单id
	 */
	public Bill getBillById(Integer id);
	
	/**
	 * 获取供应商名称集合填充到供应商下拉框
	 */
	public List<Provider> getProvider();
	
	/**
	 * 根据条件查询订单总条数
	 * @param productName 订单名称
	 * @param provderId 供应商id
	 * @param isPayment 是否支付
	 * @return
	 */
	public int getBillCount(@Param("productName") String productName,
			@Param("provderId")Integer provderId,@Param("isPayment") Integer isPayment);
	/**
	 * 根据条件获取订单集合信息
	 * @param billPage 订单分页工具类
	 * @param productName 订单名称
	 * @param provderId 供应商名称
	 * @param isPayment 是否支付
	 */
	public List<Bill> getBillInfo(@Param("page") PageUtil<Bill> billPage,@Param("productName") String productName,
				@Param("provderId")Integer provderId,@Param("isPayment") Integer isPayment);
	
}

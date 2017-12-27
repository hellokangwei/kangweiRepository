package com.smbms.biz.bill;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.bill.BillMapper;
import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.tool.PageUtil;

@Service("billBiz")
public class BillBiz {
	@Resource
	private BillMapper billMapper;
	
	/**
	 * 修改订单
	 */
	public boolean updateBill(Bill bill,Integer providerId) {
		return billMapper.updateBill(bill,providerId) > 0? true:false;
	}
	
	/**
	 * 添加订单
	 */
	public boolean addbill(Bill bill,Integer providerId) {
		return billMapper.addbill(bill,providerId) > 0? true:false;
	}
	/**
	 * 根据订单id查询订单是否存在
	 * @param id 订单id
	 * @return
	 */
	public int checkBillById(Integer id) {
		return billMapper.checkBillById(id);
	}
	/**
	 * 根据订单id删除订单
	 * @param id 订单id
	 * @return
	 */
	public boolean deleteBillById(Integer id) {
		return billMapper.deleteBillById(id) > 0? true:false;
	}
	
	/**
	 * 根据订单id获取订单信息
	 * @param id 订单id
	 */
	public  Bill getBillById(Integer id) {
		return billMapper.getBillById(id);
	}
	
	/**
	 * 获取供应商名称集合填充到供应商下拉框
	 */
	public List<Provider> getProvider() {
		return billMapper.getProvider();
	}
	
	/**
	 * 根据条件查询订单总条数
	 * @param productName 订单名称
	 * @param provderId 供应商id
	 * @param isPayment 是否支付
	 * @return
	 */
	public int getBillCount(String productName,Integer provderId,Integer isPayment) {
		return billMapper.getBillCount(productName, provderId, isPayment);
	}
	
	/**
	 * 根据条件获取订单集合信息
	 * @param billPage 订单分页工具类
	 * @param productName 订单名称
	 * @param provderId 供应商名称
	 * @param isPayment 是否支付
	 */
	public List<Bill> getBillInfo(PageUtil<Bill> billPage,String productName,
			Integer provderId,Integer isPayment) {
		billPage.setNum((billPage.getPageIndex()-1)*billPage.getPageSize());
		return billMapper.getBillInfo(billPage, productName, provderId, isPayment);
	}
	
}

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
	 * �޸Ķ���
	 */
	public boolean updateBill(Bill bill,Integer providerId) {
		return billMapper.updateBill(bill,providerId) > 0? true:false;
	}
	
	/**
	 * ��Ӷ���
	 */
	public boolean addbill(Bill bill,Integer providerId) {
		return billMapper.addbill(bill,providerId) > 0? true:false;
	}
	/**
	 * ���ݶ���id��ѯ�����Ƿ����
	 * @param id ����id
	 * @return
	 */
	public int checkBillById(Integer id) {
		return billMapper.checkBillById(id);
	}
	/**
	 * ���ݶ���idɾ������
	 * @param id ����id
	 * @return
	 */
	public boolean deleteBillById(Integer id) {
		return billMapper.deleteBillById(id) > 0? true:false;
	}
	
	/**
	 * ���ݶ���id��ȡ������Ϣ
	 * @param id ����id
	 */
	public  Bill getBillById(Integer id) {
		return billMapper.getBillById(id);
	}
	
	/**
	 * ��ȡ��Ӧ�����Ƽ�����䵽��Ӧ��������
	 */
	public List<Provider> getProvider() {
		return billMapper.getProvider();
	}
	
	/**
	 * ����������ѯ����������
	 * @param productName ��������
	 * @param provderId ��Ӧ��id
	 * @param isPayment �Ƿ�֧��
	 * @return
	 */
	public int getBillCount(String productName,Integer provderId,Integer isPayment) {
		return billMapper.getBillCount(productName, provderId, isPayment);
	}
	
	/**
	 * ����������ȡ����������Ϣ
	 * @param billPage ������ҳ������
	 * @param productName ��������
	 * @param provderId ��Ӧ������
	 * @param isPayment �Ƿ�֧��
	 */
	public List<Bill> getBillInfo(PageUtil<Bill> billPage,String productName,
			Integer provderId,Integer isPayment) {
		billPage.setNum((billPage.getPageIndex()-1)*billPage.getPageSize());
		return billMapper.getBillInfo(billPage, productName, provderId, isPayment);
	}
	
}

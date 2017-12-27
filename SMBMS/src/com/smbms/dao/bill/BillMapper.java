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
	 * �޸Ķ�����Ϣ
	 * @param bill ��������
	 * @param providerId ��Ӧ��id
	 */
	public int updateBill(@Param("bill") Bill bill,@Param("providerId")Integer providerId);
	
	/**
	 * ��Ӷ���
	 */
	public int addbill(@Param("bill") Bill bill,@Param("providerId")Integer providerId);
	
	/**
	 * ���ݶ���id��ѯ�����Ƿ����
	 * @param id ����id
	 * @return
	 */
	public int checkBillById(Integer id);
	
	/**
	 * ���ݶ���idɾ������
	 * @param id ����id
	 * @return
	 */
	public int deleteBillById(Integer id);
	
	/**
	 * ���ݶ���id��ȡ������Ϣ
	 * @param id ����id
	 */
	public Bill getBillById(Integer id);
	
	/**
	 * ��ȡ��Ӧ�����Ƽ�����䵽��Ӧ��������
	 */
	public List<Provider> getProvider();
	
	/**
	 * ����������ѯ����������
	 * @param productName ��������
	 * @param provderId ��Ӧ��id
	 * @param isPayment �Ƿ�֧��
	 * @return
	 */
	public int getBillCount(@Param("productName") String productName,
			@Param("provderId")Integer provderId,@Param("isPayment") Integer isPayment);
	/**
	 * ����������ȡ����������Ϣ
	 * @param billPage ������ҳ������
	 * @param productName ��������
	 * @param provderId ��Ӧ������
	 * @param isPayment �Ƿ�֧��
	 */
	public List<Bill> getBillInfo(@Param("page") PageUtil<Bill> billPage,@Param("productName") String productName,
				@Param("provderId")Integer provderId,@Param("isPayment") Integer isPayment);
	
}

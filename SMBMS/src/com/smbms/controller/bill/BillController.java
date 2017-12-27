package com.smbms.controller.bill;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smbms.biz.bill.BillBiz;
import com.smbms.pojo.Bill;
import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;
/**
 * �������Ŀ�����
 * @author YangMr
 */
@Controller
@RequestMapping("/sys/bill")
public class BillController {
	@Resource
	private BillBiz billBiz;
	/**
	 * ��ȡ��Ӧ�̼��ϣ���䵽������
	 */
	@RequestMapping("/getprovider")
	@ResponseBody
	public Object getProvider() {
		return JSON.toJSONString(billBiz.getProvider());
	}
	
	/**
	 * �����޸Ķ���
	 * @throws IOException 
	 */
	@RequestMapping("/billmodify")
	public void billMoeify(Bill bill,HttpSession session,HttpServletResponse response,
			@RequestParam("providerId") Integer providerId) throws IOException {
		bill.setModifyBy(((User)session.getAttribute("current_User")).getId());
		bill.setModifyDate(new Date());
		 //�����ַ�����
        response.setContentType("text/html;charset=gbk");
		if(billBiz.updateBill(bill, providerId)) {
			response.getWriter().println("<script>alert('�޸ĳɹ�');location.href='/SMBMS/sys/bill/billlist.htm';</script>");
		} else {
			response.getWriter().println("<script>alert('�޸�ʧ��');location.href='/SMBMS/sys/bill/billlist.htm';</script>");
		}
	}
	
	/**
	 * �޸�ҳ��
	 * @return
	 */
	@RequestMapping("/modifybill.htm/{id}")
	public String updateBill(@PathVariable Integer id,Model model) {
		model.addAttribute("bill", billBiz.getBillById(id));
		return "billmodify";
	}
	/**
	 * ������������
	 * @throws IOException 
	 */
	@RequestMapping("/addbill")
	public void addBill(Bill bill,HttpSession session,HttpServletResponse response,
			@RequestParam("providerId")Integer providerId) throws IOException {
		bill.setCreatedBy(((User)session.getAttribute("current_User")).getId());
		bill.setCreationDate(new Date());
		//�����ַ�����
        response.setContentType("text/html;charset=gbk");
		if(billBiz.addbill(bill, providerId)) {
			response.getWriter().println("<script>alert('���ӳɹ�');location.href='/SMBMS/sys/bill/billlist.htm';</script>");
		} else {
			response.getWriter().println("<script>alert('����ʧ��');location.href='/SMBMS/sys/bill/addbill.htm';</script>");
		}
	}
	/**
	 * ����������Ϣ
	 */
	@RequestMapping("/addbill.htm")
	public String showAddBill() {
		return "billadd";
	}
	
	/**
	 * ���ݶ���idɾ��������Ϣ
	 * @param id ����id
	 * @return
	 */
	@RequestMapping("/delbill")
	@ResponseBody
	public Object deleteBill(@RequestParam("billid") Integer id) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		if(billBiz.checkBillById(id) > 0) {
			if(billBiz.deleteBillById(id)) {
				resultMap.put("delResult", true);
			} else if (!billBiz.deleteBillById(id)) {
				resultMap.put("delResult", false);
			}
		} else {
			resultMap.put("delResult", "notexist");
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * ����id��ѯ������Ϣ
	 * @param id ����id
	 */
	@RequestMapping("/billview.htm/{id}")
	public String showBillView(@PathVariable Integer id,Model model) {
		model.addAttribute("bill",billBiz.getBillById(id));
		return "billview";
	}
	
	/**
	 * ������ҳ
	 * @param model Я����Ϣ��model����
	 * @param productName ��������
	 * @param provderId ��Ӧ��id
	 * @param isPayment �Ƿ�֧��
	 * @param pageIndex ��ǰҳ
	 */
	@RequestMapping("/billlist.htm")
	public String showBillList(Model model,
				@RequestParam(value="productName",required=false)String productName,
				@RequestParam(value="provderId",required=false)Integer provderId,
				@RequestParam(value="isPayment",required=false)Integer isPayment,
				@RequestParam(value="pageIndex",required=false)String pageIndex) {
		PageUtil<Bill> billPage = new PageUtil<Bill>();
		billPage.setTotalCount(billBiz.getBillCount(productName, provderId, isPayment));
		if(pageIndex != null) billPage.setPageIndex(Integer.parseInt(pageIndex));
		billPage.setPageList(billBiz.getBillInfo(billPage,productName, provderId, isPayment));
		
		model.addAttribute("providerList", billBiz.getProvider());
		model.addAttribute("billPage",billPage);
		model.addAttribute("productName",productName);
		model.addAttribute("provderId",provderId);
		model.addAttribute("isPayment",isPayment);
		return "billlist";
	}
}

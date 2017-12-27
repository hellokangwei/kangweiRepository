package com.smbms.controller.provider;

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

import com.alibaba.fastjson.JSONArray;
import com.smbms.biz.provider.ProviderBiz;
import com.smbms.pojo.Provider;
import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;
/**
 * ����Ӧ�̵Ŀ�����
 * @author YangMr
 */
@Controller
@RequestMapping("/sys/pro")
public class ProviderController {
	@Resource(name="providerBiz")
	private ProviderBiz providerBiz;
	
	/**
	 * ������Ӧ��
	 * @param provider ��Ӧ�̶���
	 * @throws IOException 
	 */
	@RequestMapping("/provideradd")
	public void provideradd(Provider provider,HttpServletResponse response,HttpSession session) throws IOException {
		provider.setCreatedBy(((User)session.getAttribute("current_User")).getId());
		provider.setCreationDate(new Date());
		//�����ַ�����
        response.setContentType("text/html;charset=gbk");
		if(providerBiz.addProvider(provider)) {
			response.getWriter().println("<script>alert('���ӳɹ�');location.href='/SMBMS/sys/pro/providerlist.htm';</script>");
		} else {
			response.getWriter().println("<script>alert('����ʧ��');location.href='/SMBMS/sys/bill/provideradd.htm';</script>");
		}
	}
	/**
	 * ��ʾ����ҳ��
	 * @return
	 */
	@RequestMapping("/provideradd.htm")
	public String showProviderAdd() {
		return "provideradd";
	}
	/**
	 * ɾ����Ӧ�̣�ʹ��ajax��ʽ
	 * @param id ��Ӧ��id
	 * @return ����json
	 */
	@RequestMapping("/delprovider/{id}")
	@ResponseBody
	public Object deleteProvider(@PathVariable Integer id) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		//���ù�Ӧ���Ƿ���ڶ��������ض�������
		int count = providerBiz.checkBillIsExist(id);
		if(count <= 0) {
			if(providerBiz.deleteProvider(id)) {
				resultMap.put("delResult", "true");
			} else if(!providerBiz.checkProviderIsExist(id)) {
				resultMap.put("delResult", "notexist");
			} else {
				resultMap.put("delResult", "false");
			}
		} else {
			resultMap.put("delResult", count);
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * �����޸Ĺ�Ӧ��
	 * @param provider ��Ӧ�̶���
	 */
	@RequestMapping("/providermodify")
	public void providerModify(Provider provider,HttpSession session,
				HttpServletResponse response) throws Exception {
		provider.setModifyBy(((User)session.getAttribute("current_User")).getId());
		provider.setModifyDate(new Date());
		//�����ַ�����
        response.setContentType("text/html;charset=gbk");
		if(providerBiz.updateProvider(provider)) {
			response.getWriter().print("<script>alert('�޸ĳɹ���');location.href='/SMBMS/sys/pro/providerlist.htm';</script>");
		} else {
			response.getWriter().print("<script>alert('�޸�ʧ�ܣ�');location.href='/SMBMS/sys/pro/providerlist.htm';</script>");
		}
	}
	/**
	 * ��ת���޸Ĺ�Ӧ����Ϣ�Ľ���
	 */
	@RequestMapping("/providermodify.htm/{id}")
	public String showProviderModify(Model model,@PathVariable Integer id) {
		model.addAttribute("provider",providerBiz.searchProView(id));
		return "providermodify";
	}
	/**
	 * ��ѯ��Ӧ����ͼ
	 */
	@RequestMapping("/providerview.htm/{id}")
	public String searchView(@PathVariable Integer id,Model model) {
		model.addAttribute("provider",providerBiz.searchProView(id));
		return "providerview";
	}
	/**
	 * ��ת����Ӧ�̽���,����ʾ��Ӧ����Ϣ
	 */
	@RequestMapping("/providerlist.htm")
	public String providerlist(Model model,
				@RequestParam(value="proCode",required=false) String proCode,
				@RequestParam(value="proName",required=false) String proName,
				@RequestParam(value="pageIndex",required=false) String pageIndex) {
		PageUtil<Provider> proPage = new PageUtil<Provider>();
		proPage.setTotalCount(providerBiz.getProviderCount(proCode, proName));
		//�ж��Ƿ��д�ҳ��
		if(pageIndex != null) proPage.setPageIndex(Integer.parseInt(pageIndex));
		proPage.setPageList(providerBiz.getProviderList(proPage,proCode, proName));
		model.addAttribute("proCode",proCode);
		model.addAttribute("proName",proName);
		model.addAttribute("proPage",proPage);
		return "providerlist";
	}
}

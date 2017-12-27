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
 * 处理供应商的控制类
 * @author YangMr
 */
@Controller
@RequestMapping("/sys/pro")
public class ProviderController {
	@Resource(name="providerBiz")
	private ProviderBiz providerBiz;
	
	/**
	 * 新增供应商
	 * @param provider 供应商对象
	 * @throws IOException 
	 */
	@RequestMapping("/provideradd")
	public void provideradd(Provider provider,HttpServletResponse response,HttpSession session) throws IOException {
		provider.setCreatedBy(((User)session.getAttribute("current_User")).getId());
		provider.setCreationDate(new Date());
		//设置字符编码
        response.setContentType("text/html;charset=gbk");
		if(providerBiz.addProvider(provider)) {
			response.getWriter().println("<script>alert('增加成功');location.href='/SMBMS/sys/pro/providerlist.htm';</script>");
		} else {
			response.getWriter().println("<script>alert('增加失败');location.href='/SMBMS/sys/bill/provideradd.htm';</script>");
		}
	}
	/**
	 * 显示新增页面
	 * @return
	 */
	@RequestMapping("/provideradd.htm")
	public String showProviderAdd() {
		return "provideradd";
	}
	/**
	 * 删除供应商，使用ajax方式
	 * @param id 供应商id
	 * @return 返回json
	 */
	@RequestMapping("/delprovider/{id}")
	@ResponseBody
	public Object deleteProvider(@PathVariable Integer id) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		//检查该供应商是否存在订单，返回订单条数
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
	 * 处理修改供应商
	 * @param provider 供应商对象
	 */
	@RequestMapping("/providermodify")
	public void providerModify(Provider provider,HttpSession session,
				HttpServletResponse response) throws Exception {
		provider.setModifyBy(((User)session.getAttribute("current_User")).getId());
		provider.setModifyDate(new Date());
		//设置字符编码
        response.setContentType("text/html;charset=gbk");
		if(providerBiz.updateProvider(provider)) {
			response.getWriter().print("<script>alert('修改成功！');location.href='/SMBMS/sys/pro/providerlist.htm';</script>");
		} else {
			response.getWriter().print("<script>alert('修改失败！');location.href='/SMBMS/sys/pro/providerlist.htm';</script>");
		}
	}
	/**
	 * 跳转到修改供应商信息的界面
	 */
	@RequestMapping("/providermodify.htm/{id}")
	public String showProviderModify(Model model,@PathVariable Integer id) {
		model.addAttribute("provider",providerBiz.searchProView(id));
		return "providermodify";
	}
	/**
	 * 查询供应商视图
	 */
	@RequestMapping("/providerview.htm/{id}")
	public String searchView(@PathVariable Integer id,Model model) {
		model.addAttribute("provider",providerBiz.searchProView(id));
		return "providerview";
	}
	/**
	 * 跳转到供应商界面,并显示供应商信息
	 */
	@RequestMapping("/providerlist.htm")
	public String providerlist(Model model,
				@RequestParam(value="proCode",required=false) String proCode,
				@RequestParam(value="proName",required=false) String proName,
				@RequestParam(value="pageIndex",required=false) String pageIndex) {
		PageUtil<Provider> proPage = new PageUtil<Provider>();
		proPage.setTotalCount(providerBiz.getProviderCount(proCode, proName));
		//判断是否有传页数
		if(pageIndex != null) proPage.setPageIndex(Integer.parseInt(pageIndex));
		proPage.setPageList(providerBiz.getProviderList(proPage,proCode, proName));
		model.addAttribute("proCode",proCode);
		model.addAttribute("proName",proName);
		model.addAttribute("proPage",proPage);
		return "providerlist";
	}
}

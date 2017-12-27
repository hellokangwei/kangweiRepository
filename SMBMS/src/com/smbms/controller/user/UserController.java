package com.smbms.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.smbms.biz.user.UserBiz;
import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;
/**
 * �û�����
 * @author YangMr
 *
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {
	@Resource
	private UserBiz userBiz;
	
	/**
	 * ɾ���û�
	 * @param uid �û�id
	 */
	@RequestMapping("/deluser")
	@ResponseBody
	public Object delUserById(String uid) {
        HashMap<String,String> resultMap = new HashMap<String,String>();
        if(StringUtils.isNullOrEmpty(uid))
            resultMap.put("delResult", "notexist");
        else
        if(userBiz.delUserById(uid))
            resultMap.put("delResult", "true");
        else
            resultMap.put("delResult", "false");
        return JSONArray.toJSONString(resultMap);
    }

	/**
	 * ����û������Ƿ����
	 * @param userCode �û�����
	 */
	@RequestMapping("/ucexist")
	@ResponseBody
    public Object userCodeIsExit(@RequestParam("userCode") String userCode)
    {
        HashMap<String,String> resultMap = new HashMap<String,String>();
        if(StringUtils.isNullOrEmpty(userCode))
        {
            resultMap.put("userCode", "NULL");
        } else
        {
            User user = userBiz.checkUserByUserCode(userCode);
            if(user != null)
                resultMap.put("userCode", "exist");
            else
                resultMap.put("userCode", "noexist");
        }
        return JSONArray.toJSONString(resultMap);
    }

    /**
     * �����û��޸�
     * @param user �û�����
     * @return
     * @throws IOException 
     */
    @RequestMapping("/usermodify")
    public void usermodify(User user,@RequestParam(value="roleId",required=false) Integer userRole, HttpSession session,HttpServletResponse response) throws IOException
    {
        user.setModifyBy(((User)session.getAttribute("current_User")).getId());
        user.setModifyDate(new Date());
        //�����ַ�����
        response.setContentType("text/html;charset=gbk");
        if(userBiz.usermodify(user,userRole)) {
        	response.getWriter().println("<script>alert('�޸ĳɹ�');location.href='/SMBMS/sys/user/userlist.htm';</script>");
        } else {
        	response.getWriter().println("<script>alert('�޸�ʧ��');location.href='/SMBMS/sys/user/usermodify.htm';</script>");
        }
    }

    /**
     * �û��޸Ľ���
     */
    @RequestMapping("/usermodify.htm")
    public String updateUser(@RequestParam("id") Integer id, Model model)
    {
        model.addAttribute("user", userBiz.getUserById(id));
        model.addAttribute("roleList", userBiz.getRoleList());
        return "usermodify";
    }

    /**
     * �鿴�û���ͼ,ʹ��ajax��ʽ
     * @param id �û�id
     */
    @RequestMapping("/userview")
    @ResponseBody
    public User userView(@RequestParam("id")Integer id) {
        return userBiz.getUserById(id);
    }

    /**
     * ��鵱ǰ�û����������Ƿ���ȷ
     * @param oldpassword ������
     */
    @RequestMapping("/checkpwd") 
    @ResponseBody
    public Object checkpwd(@RequestParam("oldpassword")String oldpassword, HttpSession session) {
        User currentUser = (User)session.getAttribute("current_User");
        HashMap<String,String> resultMap = new HashMap<String,String>();
        if(StringUtils.isNullOrEmpty(oldpassword))
        {
            resultMap.put("result", "NULL");
        } else {
            if(currentUser == null)
            {
                resultMap.put("result", "sessionerror");
                return "redirect:/sys/user/login.htm";
            }
            if(userBiz.checkUserPwd(oldpassword, currentUser.getId()))
                resultMap.put("result", "true");
            else if(!userBiz.checkUserPwd(oldpassword, currentUser.getId()))
                resultMap.put("result", "false");
            else
                resultMap.put("result", "error");
        }
        return JSONArray.toJSONString(resultMap);
    }

    /**
     * �����û��޸�����
     * @param newpassword ������
     * @throws IOException 
     */
    @RequestMapping("/updatepwd")
    public void updatePwd(@RequestParam("newpassword") String newpassword,HttpServletResponse response,
    		HttpSession session) throws IOException {
    	//�����ַ�����
    	response.setContentType("text/html;charset=gbk");
        if(userBiz.updatePwd(newpassword, ((User)session.getAttribute("current_User")).getId()))
        {
            session.removeAttribute("current_User");
            response.getWriter().println("<script>alert('�޸�����ɹ�');location.href='/SMBMS/sys/user/login.htm';</script>");
        } else
        {
            response.getWriter().println("<script>alert('�޸�����ʧ��');location.href='/SMBMS/sys/user/pwdmodify.htm';</script>");
        }             
    }

    /**
     * ��ת�޸��û�����
     */
    @RequestMapping("/pwdmodify.htm")
    public String pwdmodify()
    {
        return "pwdmodify";
    }

    /**
     * ����ҳ��ʹ��ajax��ʽ����Ӵ�û���ɫ
     */
    @RequestMapping("/rolelist")
    @ResponseBody
    public Object getRoleList() {
    	return JSON.toJSONString(userBiz.getRoleList());
    }
    
    /**
     * �����û�����
     * @param user �û�����
     * @param attachs ͼƬ����
     * @throws IOException 
     */
    @RequestMapping("/adduser")
    public void useradd(User user,Model model,HttpServletResponse response,
			HttpSession session,HttpServletRequest request,
			@RequestParam(value="attachs",required=false)MultipartFile[] attachs) throws IOException {
        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;
        boolean flag = true;
        String path = request.getSession().getServletContext().getRealPath((new StringBuilder("statics")).append(File.separator).append("upload").toString());
        for(int i = 0; i < attachs.length; i++)
        {
            MultipartFile attach = attachs[i];
            if(!attach.isEmpty())
            {
                if(i == 0)
                    errorInfo = "uploadFileError";
                else
                if(i == 1)
                    errorInfo = "uploadWpFileError";
                String oldFileName = attach.getOriginalFilename();
                String prefix = FilenameUtils.getExtension(oldFileName);
                int filesize = 500000;
                if(attach.getSize() > (long)filesize)
                {
                	request.setAttribute(errorInfo, " * �ϴ�ͼƬ��С���ܳ��� 500KB");
                    flag = false;
                } else
                if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("png") || prefix.equalsIgnoreCase("pneg"))
                {
                    String fileName = (new StringBuilder(String.valueOf(System.currentTimeMillis() + (long)RandomUtils.nextInt(1000000)))).append("_Personal.jpg").toString();
                    File targetFile = new File(path, fileName);
                    if(!targetFile.exists())
                        targetFile.mkdirs();
                    try
                    {
                        attach.transferTo(targetFile);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        request.setAttribute(errorInfo, " * �ϴ�ͼƬʧ��");
                        flag = false;
                    }
                    if(i == 0)
                        idPicPath = (new StringBuilder(String.valueOf(path))).append(File.separator).append(fileName).toString();
                    else
                    if(i == 1)
                        workPicPath = (new StringBuilder(String.valueOf(path))).append(File.separator).append(fileName).toString();
                } else {
                	request.setAttribute(errorInfo, " * �ϴ�ͼƬ��ʽ����ȷ");
                    flag = false;
                }
            }
        }
        if(flag)
        {
            user.setCreatedBy(((User)session.getAttribute("current_User")).getId());
            user.setCreationDate(new Date());
            user.setIdPicPath(idPicPath);
            user.setWorkPicPath(workPicPath);
            //�����ַ�����
            response.setContentType("text/html;charset=gbk");
            if(userBiz.addUser(user)) {
            	response.getWriter().println("<script>alert('���ӳɹ�');location.href='/SMBMS/sys/user/userlist.htm';</script>");
            } else {
            	response.getWriter().println("<script>alert('����ʧ��');location.href='/SMBMS/sys/user/useradd.htm';</script>");
            }
        }
    }

    /**
     * �����û�����
     */
    @RequestMapping("/useradd.htm")
    public String userAdd(Model model)
    {
        model.addAttribute("roleList", userBiz.getRoleList());
        return "useradd";
    }

    /**
     * �û���Ϣ����
     * @param userName �û�����
     * @param roleId �û���ɫID
     * @param pageIndex ��ǰҳ��
     * @return
     */
    @RequestMapping("/userlist.htm")
    public String userlist(Model model,@RequestParam(value="userName",required=false) String userName,
    					@RequestParam(value="roleId",required=false) Integer roleId,
    					@RequestParam(value="pageIndex",required=false) Integer pageIndex) {
    	//������ҳ������
        PageUtil<User> userPage = new PageUtil<User>();
        userPage.setTotalCount(userBiz.getUserCount(userName, roleId));//��ѯ������
        if(pageIndex != null)
            userPage.setPageIndex(pageIndex);
        List<User> userList = userBiz.getUserList(userPage, userName, roleId);//��ȡ���󼯺�
        userPage.setPageList(userList); //�Ѷ��󼯺ϱ��浽��ҳ������
        
        model.addAttribute("userPage", userPage);
        model.addAttribute("roleList", userBiz.getRoleList());
        model.addAttribute("userName", userName);
        model.addAttribute("roleId", roleId);
        return "userlist";
    }

    /**
     * ע��
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("current_User");
        return "login";
    }

    /**
     * ��ת����¼ҳ��
     */
    @RequestMapping("/login.htm")
    public String login()
    {
        return "login";
    }

    /**
     * ��ת��������
     */
    @RequestMapping("/frame.htm")
    public String frame()
    {
        return "frame";
    }
}

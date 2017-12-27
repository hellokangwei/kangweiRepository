package com.smbms.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smbms.biz.user.UserBiz;
import com.smbms.pojo.User;

@Controller
@RequestMapping("/login")
public class UserLoginController {
	@Resource
	private UserBiz userBiz;
	
	/**
	 * �����¼
	 */
	@RequestMapping(value="/doLogin")
    public String doLogin(String userCode, String userPassword, 
    		HttpSession session, HttpServletRequest request) {
        User user = userBiz.login(userCode, userPassword);
        if(user != null)
        {
            session.setAttribute("current_User", user);
            return "redirect:/sys/user/frame.htm";
        } else {
            request.setAttribute("error", "�û��������벻��ȷ");
            return "login";
        }
    }
    
}

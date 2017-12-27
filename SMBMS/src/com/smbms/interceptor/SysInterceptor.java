package com.smbms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.smbms.pojo.User;
/**
 * À¹½ØÆ÷£¬À¹½ØÓÃ»§ÊÇ·ñµÇÂ¼
 * @author YangMr
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		        throws Exception {
		        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("current_User");
        if(user == null) {
            response.sendRedirect(request.getContextPath()+"/notlogin.jsp");
            return false;
        } else {
            return true;
        }
	}
}

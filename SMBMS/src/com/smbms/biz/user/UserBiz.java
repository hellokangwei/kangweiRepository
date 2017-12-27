package com.smbms.biz.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.user.UserMapper;
import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;

/**
 * 用户业务逻辑层
 * @author yy
 */
@Service("userBiz")
public class UserBiz {
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 修改用户
	 * @param user 用户对象
	 */
	public boolean usermodify(User user,Integer userRole) {
        return userMapper.usermodify(user,userRole) > 0 ? true:false;
    }

	/**
	 * 修改用户密码
	 * @param newPassword 新密码
	 * @param id 用户id
	 */
    public boolean updatePwd(String newpassword, Integer id) {
        return userMapper.updatePwd(newpassword, id) > 0 ? true:false;
    }

    /**
     * 检查用户密码
     * @param userPassword 用户密码
     * @param id 用户id
     */
    public boolean checkUserPwd(String userPassword, Integer id) {
        return userMapper.checkUserPwd(userPassword, id) > 0 ? true:false;
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    public boolean delUserById(String uid)
    {
        return userMapper.delUserById(uid) > 0 ? true:false;
    }

    /**
     * 添加用户
     * @param user 用户对象
     */
    public boolean addUser(User user)
    {
        return userMapper.addUser(user) > 0 ? true:false;
    }
    /**
     * 检查用户编码是否存在
     * @param userCode 用户编码
     */
    public User checkUserByUserCode(String userCode)
    {
        return userMapper.checkUserByUserCode(userCode);
    }

    /**
     * 根据用户id获取用户对象
     * @param id 用户id
     */
    public User getUserById(Integer id)
    {
        return userMapper.getUserById(id);
    }
    /**
     * 获取用户角色对象集合
     */
    public List<User> getRoleList()
    {
        return userMapper.getRoleList();
    }

    /**
     * 根据用户名称和用户角色id获取用户信息的总信息
     * @param userName 用户名称
     * @param userRole 用户角色id
     */
    public int getUserCount(String userName, Integer roleId)
    {
        return userMapper.getUserCount(userName, roleId);
    }
    /**
     * 根据用户名称和用户角色id获取用户信息的集合
     * @param userName 用户名称
     * @param userRole 用户角色id
     * @param page 用户分页工具类
     */
    public List<User> getUserList(PageUtil<User> page, String userName, Integer userRole)
    {
        page.setNum(Integer.valueOf((page.getPageIndex().intValue() - 1) * page.getPageSize().intValue()));
        return userMapper.getUserList(page, userName, userRole);
    }
    /**
     * 用户登录
     * @param userCode 用户编码
     * @param userPassword 用户密码
     */
    public User login(String userCode, String userPassword)
    {
        return userMapper.login(userCode, userPassword);
    }

   
}

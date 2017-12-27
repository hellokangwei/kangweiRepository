package com.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;

@Repository
public interface UserMapper {
	/**
	 * 修改用户
	 * @param user 用户对象
	 */
	public int usermodify(@Param("user") User user,@Param("userRole")Integer userRole);

	/**
	 * 修改用户密码
	 * @param newPassword 新密码
	 * @param id 用户id
	 */
    public int updatePwd(@Param("newPassword") String newPassword, @Param("id") Integer id);

    /**
     * 检查用户密码
     * @param userPassword 用户密码
     * @param id 用户id
     */
    public int checkUserPwd(@Param("userPassword") String userPassword, @Param("id") Integer id);

    /**
     * 删除用户
     * @param id 用户id
     */
    public int delUserById(String id);

    /**
     * 添加用户
     * @param user 用户对象
     */
    public int addUser(@Param("user") User user);

    /**
     * 检查用户编码是否存在
     * @param userCode 用户编码
     */
    public User checkUserByUserCode(String userCode);

    /**
     * 根据用户id获取用户对象
     * @param id 用户id
     */
    public User getUserById(Integer id);

    /**
     * 获取用户角色对象集合
     */
    public List<User> getRoleList();

    /**
     * 根据用户名称和用户角色id获取用户信息的总信息
     * @param userName 用户名称
     * @param userRole 用户角色id
     */
    public int getUserCount(@Param("userName")String userName, @Param("userRole")Integer userRole);

    /**
     * 根据用户名称和用户角色id获取用户信息的集合
     * @param userName 用户名称
     * @param userRole 用户角色id
     * @param page 用户分页工具类
     */
    public List<User> getUserList(@Param("page") PageUtil<User> page, @Param("userName")String userName,@Param("userRole") Integer userRole);

    /**
     * 用户登录
     * @param userCode 用户编码
     * @param userPassword 用户密码
     */
    public User login(@Param("userCode") String userCode, @Param("userPassword")String userPassword);
}

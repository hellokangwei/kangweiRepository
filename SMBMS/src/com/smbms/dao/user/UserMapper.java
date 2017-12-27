package com.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;

@Repository
public interface UserMapper {
	/**
	 * �޸��û�
	 * @param user �û�����
	 */
	public int usermodify(@Param("user") User user,@Param("userRole")Integer userRole);

	/**
	 * �޸��û�����
	 * @param newPassword ������
	 * @param id �û�id
	 */
    public int updatePwd(@Param("newPassword") String newPassword, @Param("id") Integer id);

    /**
     * ����û�����
     * @param userPassword �û�����
     * @param id �û�id
     */
    public int checkUserPwd(@Param("userPassword") String userPassword, @Param("id") Integer id);

    /**
     * ɾ���û�
     * @param id �û�id
     */
    public int delUserById(String id);

    /**
     * ����û�
     * @param user �û�����
     */
    public int addUser(@Param("user") User user);

    /**
     * ����û������Ƿ����
     * @param userCode �û�����
     */
    public User checkUserByUserCode(String userCode);

    /**
     * �����û�id��ȡ�û�����
     * @param id �û�id
     */
    public User getUserById(Integer id);

    /**
     * ��ȡ�û���ɫ���󼯺�
     */
    public List<User> getRoleList();

    /**
     * �����û����ƺ��û���ɫid��ȡ�û���Ϣ������Ϣ
     * @param userName �û�����
     * @param userRole �û���ɫid
     */
    public int getUserCount(@Param("userName")String userName, @Param("userRole")Integer userRole);

    /**
     * �����û����ƺ��û���ɫid��ȡ�û���Ϣ�ļ���
     * @param userName �û�����
     * @param userRole �û���ɫid
     * @param page �û���ҳ������
     */
    public List<User> getUserList(@Param("page") PageUtil<User> page, @Param("userName")String userName,@Param("userRole") Integer userRole);

    /**
     * �û���¼
     * @param userCode �û�����
     * @param userPassword �û�����
     */
    public User login(@Param("userCode") String userCode, @Param("userPassword")String userPassword);
}

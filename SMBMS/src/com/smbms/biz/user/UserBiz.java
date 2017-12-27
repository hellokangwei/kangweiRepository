package com.smbms.biz.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smbms.dao.user.UserMapper;
import com.smbms.pojo.User;
import com.smbms.tool.PageUtil;

/**
 * �û�ҵ���߼���
 * @author yy
 */
@Service("userBiz")
public class UserBiz {
	@Resource
	private UserMapper userMapper;
	
	/**
	 * �޸��û�
	 * @param user �û�����
	 */
	public boolean usermodify(User user,Integer userRole) {
        return userMapper.usermodify(user,userRole) > 0 ? true:false;
    }

	/**
	 * �޸��û�����
	 * @param newPassword ������
	 * @param id �û�id
	 */
    public boolean updatePwd(String newpassword, Integer id) {
        return userMapper.updatePwd(newpassword, id) > 0 ? true:false;
    }

    /**
     * ����û�����
     * @param userPassword �û�����
     * @param id �û�id
     */
    public boolean checkUserPwd(String userPassword, Integer id) {
        return userMapper.checkUserPwd(userPassword, id) > 0 ? true:false;
    }

    /**
     * ɾ���û�
     * @param id �û�id
     */
    public boolean delUserById(String uid)
    {
        return userMapper.delUserById(uid) > 0 ? true:false;
    }

    /**
     * ����û�
     * @param user �û�����
     */
    public boolean addUser(User user)
    {
        return userMapper.addUser(user) > 0 ? true:false;
    }
    /**
     * ����û������Ƿ����
     * @param userCode �û�����
     */
    public User checkUserByUserCode(String userCode)
    {
        return userMapper.checkUserByUserCode(userCode);
    }

    /**
     * �����û�id��ȡ�û�����
     * @param id �û�id
     */
    public User getUserById(Integer id)
    {
        return userMapper.getUserById(id);
    }
    /**
     * ��ȡ�û���ɫ���󼯺�
     */
    public List<User> getRoleList()
    {
        return userMapper.getRoleList();
    }

    /**
     * �����û����ƺ��û���ɫid��ȡ�û���Ϣ������Ϣ
     * @param userName �û�����
     * @param userRole �û���ɫid
     */
    public int getUserCount(String userName, Integer roleId)
    {
        return userMapper.getUserCount(userName, roleId);
    }
    /**
     * �����û����ƺ��û���ɫid��ȡ�û���Ϣ�ļ���
     * @param userName �û�����
     * @param userRole �û���ɫid
     * @param page �û���ҳ������
     */
    public List<User> getUserList(PageUtil<User> page, String userName, Integer userRole)
    {
        page.setNum(Integer.valueOf((page.getPageIndex().intValue() - 1) * page.getPageSize().intValue()));
        return userMapper.getUserList(page, userName, userRole);
    }
    /**
     * �û���¼
     * @param userCode �û�����
     * @param userPassword �û�����
     */
    public User login(String userCode, String userPassword)
    {
        return userMapper.login(userCode, userPassword);
    }

   
}

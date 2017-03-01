package wuye.manager.user.dao;

import java.util.List;

import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.PageUtil;

public interface ManagerUserDao {

	/**
	 * 更改用户密码
	 * @param cn
	 * @param passwd
	 * @return
	 */
	public boolean updateUserPasswd(String cn,String passwd);
	
	/**
	 * 添加用户
	 * @param userBean
	 * @return
	 */
	public boolean addUser(UserBean userBean);
	
	/**
	 * 查询用户
	 * @param userBean
	 * @return
	 */
	public List<UserBean> findAllUser(UserBean userBean,PageUtil page);
	
	
	public UserBean getUserInfo(int id);
	/**
	 * 查询用户
	 * @param userBean
	 * @return
	 */
	public int getUserTotal(UserBean userBean);
	
	/**
	 * 更新用户信息
	 * @param userBean
	 * @return
	 */
	public void updateUserInfo(UserBean userBean);
	
}

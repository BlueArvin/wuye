package wuye.manager.user.logic;

import java.util.List;

import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.PageUtil;

public interface ManagerUserLogic {

	/**
	 * 更改密码
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
	 * 更新用户信息
	 * @param userBean
	 * @return
	 */
	public void updateUserInfo(UserBean userBean);
}

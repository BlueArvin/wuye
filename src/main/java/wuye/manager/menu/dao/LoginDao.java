package wuye.manager.menu.dao;

import wuye.manager.user.bean.UserBean;

public interface LoginDao {
	public UserBean checkUser(String userName,String passwd);
}

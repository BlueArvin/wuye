package wuye.manager.menu.logic;

import wuye.manager.user.bean.UserBean;

public interface LoginLogic {
	public UserBean checkUser(String loginName,String passwd);
}

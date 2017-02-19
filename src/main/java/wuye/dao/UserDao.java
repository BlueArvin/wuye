package wuye.dao;

import wuye.bean.LoginUserBean;

public interface UserDao {

	public int login(LoginUserBean user);
	
	public int validateUserPWD(LoginUserBean user);
	
	public int changePWD(LoginUserBean user, String newpwd);
}

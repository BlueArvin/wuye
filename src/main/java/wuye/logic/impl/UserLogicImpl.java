package wuye.logic.impl;

import wuye.bean.LoginUserBean;
import wuye.dao.UserDao;
import wuye.logic.UserLogic;

public class UserLogicImpl implements UserLogic{
	
	private UserDao userDao;
	
	public int UserLogin(LoginUserBean bean) {
		return userDao.login(bean);
	}

}

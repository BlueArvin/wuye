package wuye.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import wuye.bean.LoginUserBean;
import wuye.dao.UserDao;
import wuye.logic.UserLogic;

public class UserLogicImpl implements UserLogic{
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private UserDao userDao;
	
	public int UserLogin(LoginUserBean bean) {
		return userDao.login(bean);
	}

}

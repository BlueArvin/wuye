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

	// 修改密码
	public int UserChangepwd(LoginUserBean user, String pwd) {
		// 验证愿密码是否正确
		int ret1 = userDao.validateUserPWD(user);
		// 更换新密码
		if(user.getRet() == 0) {
			int ret2 = userDao.changePWD(user, pwd);
			return ret2;
		}else {
			return ret1;
		}
	}

}

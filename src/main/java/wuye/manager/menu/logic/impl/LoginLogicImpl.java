package wuye.manager.menu.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.menu.dao.LoginDao;
import wuye.manager.menu.logic.LoginLogic;
import wuye.manager.user.bean.UserBean;

@Service("loginLogic")
public class LoginLogicImpl implements LoginLogic {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public UserBean checkUser(String loginName, String passwd) {
		return loginDao.checkUser(loginName, passwd);
	}

	@Override
	public void updateSysVersion(boolean version,boolean checkversion){
		loginDao.updateSysVersion(version,checkversion);
	}
	
	
}

package wuye.manager.user.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.user.bean.UserBean;
import wuye.manager.user.dao.ManagerUserDao;
import wuye.manager.user.logic.ManagerUserLogic;
import wuye.manager.utils.PageUtil;


@Service("managerUserLogic")
public class ManagerUserLogicImpl implements ManagerUserLogic {

	@Autowired
	private ManagerUserDao managerUserDao;
	
	@Override
	public boolean updateUserPasswd(String cn,String passwd){
		return managerUserDao.updateUserPasswd(cn,passwd);
	}

	@Override
	public boolean addUser(UserBean userBean) {
		return managerUserDao.addUser(userBean);
	}

	@Override
	public List<UserBean> findAllUser(UserBean userBean, PageUtil page) {
		int count = managerUserDao.getUserTotal(userBean);
		page.setTotal(count);
		
		return managerUserDao.findAllUser(userBean, page);
	}

	@Override
	public UserBean getUserInfo(int id){
		return managerUserDao.getUserInfo(id);
	}
	
	@Override
	public void updateUserInfo(UserBean userBean) {
		managerUserDao.updateUserInfo(userBean);
	}

	@Override
	public boolean checkUserCn(String loginName,int count) {
		return managerUserDao.checkUserCn(loginName,count);
	}
}

package wuye.manager.menu.logic;

import wuye.manager.user.bean.UserBean;

public interface LoginLogic {
	public UserBean checkUser(String loginName,String passwd);
	
	
	/**
	 * 更新版本
	 * @param version
	 * @param checkversion
	 */
	public void updateSysVersion(boolean version,boolean checkversion);
	
}

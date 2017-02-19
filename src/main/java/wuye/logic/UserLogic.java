package wuye.logic;

import wuye.bean.LoginUserBean;

public interface UserLogic {
	
	public int UserLogin(LoginUserBean bean);
	
	public int UserChangepwd(LoginUserBean bean, String pwd);

}

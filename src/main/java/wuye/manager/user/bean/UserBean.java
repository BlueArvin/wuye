package wuye.manager.user.bean;

import java.util.List;

public class UserBean {
	private String cn;
	private String userName;
	private List<String> roleList;
	
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	@Override
	public String toString() {
		return "UserBean [cn=" + cn + ", userName=" + userName + ", roleList="
				+ roleList + "]";
	}
	
}
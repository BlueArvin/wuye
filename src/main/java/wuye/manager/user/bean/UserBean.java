package wuye.manager.user.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

public class UserBean {
	private int id;
	private String cn;
	private String userName;
	private String passwd;
	private Integer status;
	private int role;//app权限
	private String roleName;//app权限
	private String webRole;//web权限
	private String webRoleName;//web权限
	private List<String> roleList;//web权限列表
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getRoleName() {
		return roleName==null?"":roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getWebRoleName() {
		return webRoleName==null?"":webRoleName;
	}
	public void setWebRoleName(String webRoleName) {
		this.webRoleName = webRoleName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
		String name = "";
		if((role&2)!=0){
			name += "项目检查  ";
		}
		if((role&1)!=0){
			name += "记录查询 ";
		}
		this.roleName = name;
	}
	
	public String getWebRole() {
		return webRole;
	}
	public void setWebRole(String webRole) {
		this.webRole = webRole;
		roleList = new ArrayList<String>();
		if(webRole!=null&&!"".equals(webRole)){
			String name = "";
			String[] webRoles = webRole.split(",");
			for(int i=0;i<webRoles.length;i++){
				roleList.add(webRoles[i]);
				name+=UserRoleEnum.getName(webRoles[i])+" ";
			}
			this.webRoleName = name;
		}
		
	}
	public List<String> getRoleList() {
		return roleList;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", cn=" + cn + ", userName=" + userName
				+ ", passwd=" + passwd + ", status=" + status + ", role="
				+ role + ", roleName=" + roleName + ", webRole=" + webRole
				+ ", webRoleName=" + webRoleName + ", roleList=" + roleList
				+ "]";
	}
	
}
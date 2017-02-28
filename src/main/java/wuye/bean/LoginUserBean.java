package wuye.bean;

import java.util.HashMap;
import java.util.Map;

import wuye.api.bean.RetBean;

/**
 * 用户登录 Bean
 * @author lujinfei
 * 2017-02-15
 */
public class LoginUserBean {
	public static final int OK = 0;
	public static final int NOUSER = 1;
	public static final int PWDERROR = 2;
	public static final int COOKIEFAIL = 3;
	
	private int userid = 0;
	private String account = "";
	private String pwd = "";   	// ���������洢���������md5ֵ
	private String cookie = "";	// ��¼���cookieֵ
	private int type = 0;      	// 0. �˺�������ͨ��¼    1. ͨ��cookieֵ��¼
	private int role = 0;       // 权限
	
	private int ret = 1;            // ��Ž��
	private String updateCookie = ""; // �����ɵ�cookie
	
	public String getUpdateCookie() {
		return updateCookie;
	}

	public void setUpdateCookie(String updateCookie) {
		this.updateCookie = updateCookie;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return userid;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getAccount() {
		return account;
	}

	public String getPwd() {
		return pwd;
	}

	public String getCookie() {
		return cookie;
	}

	public int getType() {
		return type;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public LoginUserBean(String account, String secret, int type) {
		if(type == 0) {
			this.account = account;
			this.pwd = secret;
			this.type = 0;
		} else {
			this.account = account;
			this.cookie = secret;
			this.type = 1;
		}
	}
	
	public RetBean toAPIresult() {
		RetBean bean = null;
		switch(ret) {
		case OK:
			bean = new RetBean(ret, "登录成功");
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("right", role);
			value.put("token", updateCookie);
			bean.setValue(value);
			break;
		case NOUSER:
		case PWDERROR:
			bean = new RetBean(ret, "用户名或密码错误");
			break;
		case COOKIEFAIL:
			bean = new RetBean(ret, "登录失效");
			break;
		default:
			bean = new RetBean(ret, "登录错误");
		}
		return bean;
	}
}

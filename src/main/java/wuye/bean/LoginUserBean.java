package wuye.bean;

import wuye.api.bean.RetBean;

/**
 * 用户登录Bean
 * @author lujinfei
 * 2017-02-15
 */
public class LoginUserBean {
	public static final int OK = 0;
	public static final int NOUSER = 1;
	public static final int PWDERROR = 2;
	public static final int COOKIEFAIL = 3;
	
	private String account = "";
	private String pwd = "";   	// 这里的密码存储的是密码的md5值
	private String cookie = "";	// 登录后的cookie值
	private int type = 0;      	// 0. 账号密码普通登录    1. 通过cookie值登录
	
	private int ret = 0;            // 存放结果
	private String updateCookie = ""; // 新生成的cookie
	
	public String getUpdateCookie() {
		return updateCookie;
	}

	public void setUpdateCookie(String updateCookie) {
		this.updateCookie = updateCookie;
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
		switch(getType()) {
		case OK:
			bean = new RetBean(getType(), "登录成功");
			bean.setValue(updateCookie);
			break;
		case NOUSER:
		case PWDERROR:
			bean = new RetBean(getType(), "用户不存在或密码错误");
			break;
		case COOKIEFAIL:
			bean = new RetBean(getType(), "登录失效");
			break;
		default:
			bean = new RetBean(getType(), "无效");
		}
		return bean;
	}
}

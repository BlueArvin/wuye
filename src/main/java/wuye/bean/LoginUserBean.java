package wuye.bean;

import wuye.api.bean.RetBean;

/**
 * �û���¼Bean
 * @author lujinfei
 * 2017-02-15
 */
public class LoginUserBean {
	public static final int OK = 0;
	public static final int NOUSER = 1;
	public static final int PWDERROR = 2;
	public static final int COOKIEFAIL = 3;
	
	private String account = "";
	private String pwd = "";   	// ���������洢���������md5ֵ
	private String cookie = "";	// ��¼���cookieֵ
	private int type = 0;      	// 0. �˺�������ͨ��¼    1. ͨ��cookieֵ��¼
	
	private int ret = 0;            // ��Ž��
	private String updateCookie = ""; // �����ɵ�cookie
	
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
			bean = new RetBean(getType(), "��¼�ɹ�");
			bean.setValue(updateCookie);
			break;
		case NOUSER:
		case PWDERROR:
			bean = new RetBean(getType(), "�û������ڻ��������");
			break;
		case COOKIEFAIL:
			bean = new RetBean(getType(), "��¼ʧЧ");
			break;
		default:
			bean = new RetBean(getType(), "��Ч");
		}
		return bean;
	}
}

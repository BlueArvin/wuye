package wuye.manager.menu.bean;

import java.util.List;

public class MenuBean {
	private String menuCode;
	private String menuName;
	private String url;
	private String parentCode;
	private int ordr;
	List<MenuBean> child;
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public int getOrdr() {
		return ordr;
	}
	public void setOrdr(int ordr) {
		this.ordr = ordr;
	}
	public List<MenuBean> getChild() {
		return child;
	}
	public void setChild(List<MenuBean> child) {
		this.child = child;
	}
}

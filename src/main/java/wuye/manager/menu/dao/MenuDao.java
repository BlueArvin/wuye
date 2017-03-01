package wuye.manager.menu.dao;

import java.util.List;

import wuye.manager.menu.bean.MenuBean;

public interface MenuDao {
	public List<MenuBean> findAllMenu(List<String> menuCode);
	
	public void findMenuByParentCode(MenuBean menuBean);
}

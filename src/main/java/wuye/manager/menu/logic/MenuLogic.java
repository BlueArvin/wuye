package wuye.manager.menu.logic;

import java.util.List;

import wuye.manager.menu.bean.MenuBean;

public interface MenuLogic {
	
	public List<MenuBean> findAllMenu(List<String> menuCode);
	
}

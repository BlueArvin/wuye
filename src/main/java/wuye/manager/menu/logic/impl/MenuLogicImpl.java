package wuye.manager.menu.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.menu.bean.MenuBean;
import wuye.manager.menu.logic.MenuLogic;
import wuye.manager.menu.dao.MenuDao;

@Service("menuLogic")
public class MenuLogicImpl implements MenuLogic {

	@Autowired
	private MenuDao menuDao;
	
	public List<MenuBean> findAllMenu(List<String> menuCode){
		List<MenuBean> list = menuDao.findAllMenu(menuCode);
		for(int i=0;i<list.size();i++){
			MenuBean mb = list.get(i);
			menuDao.findMenuByParentCode(mb);
		}
		
		return list;
	}

	
	
}

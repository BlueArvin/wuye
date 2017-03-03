package wuye.manager.norm.logic.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import wuye.manager.norm.bean.AreaBean;
import wuye.manager.norm.logic.AreaLogic;
import wuye.manager.utils.PageUtil;

@Service("areaLogic")
public class AreaLogicImpl implements AreaLogic {

	@Override
	public boolean addArea(AreaBean areaBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArea(AreaBean areaBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteArea(int type, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AreaBean> queryAreaList(int type,PageUtil page) {
		// TODO Auto-generated method stub
		return null;
	}


}

package wuye.manager.norm.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.norm.bean.AreaBean;
import wuye.manager.norm.dao.AreaDao;
import wuye.manager.norm.logic.AreaLogic;
import wuye.manager.utils.PageUtil;

@Service("areaLogic")
public class AreaLogicImpl implements AreaLogic {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public boolean addArea(AreaBean areaBean) {
		boolean ret = false;
		switch (areaBean.getType()) {
		case 1:
			ret = areaDao.addState(areaBean);
			break;
		case 2:
			ret = areaDao.addStreet(areaBean);
			break;
		case 3:
			ret = areaDao.addPianqu(areaBean);
			break;
		case 4:
			ret = areaDao.addHutong(areaBean);
			break;
		case 5:
			ret = areaDao.addCompany(areaBean);
			break;
		default:
			break;
		}
		return ret;
	}

	@Override
	public boolean updateArea(AreaBean areaBean) {
		boolean ret = false;
		switch (areaBean.getType()) {
		case 1:
			ret = areaDao.updateState(areaBean);
			break;
		case 2:
			ret = areaDao.updateStreet(areaBean);
			break;
		case 3:
			ret = areaDao.updatePianqu(areaBean);
			break;
		case 4:
			ret = areaDao.updateHutong(areaBean);
			break;
		case 5:
			ret = areaDao.updateCompany(areaBean);
			break;
		default:
			break;
		}
		return ret;
	}

	@Override
	public void deleteArea(int type, int id) {
		String tableName="";
		switch (type) {
		case 1:
			tableName = "t_state";
			break;
		case 2:
			tableName = "t_street";
			break;
		case 3:
			tableName = "t_pianqu";
			break;
		case 4:
			tableName = "t_hutong";
			break;
		case 5:
			tableName = "t_managecompany";
			break;
		default:
			break;
		}
		areaDao.delArea(tableName, id);
	}

	@Override
	public List<AreaBean> queryAreaList(int type,PageUtil page) {
		List<AreaBean> ret = new ArrayList<AreaBean>();
		String tableName = "";
		switch (type) {
		case 1:
			tableName = "t_state";
			ret = areaDao.queryStateList(page);
			break;
		case 2:
			tableName = "t_street";
			ret = areaDao.queryStreetList(page);
			break;
		case 3:
			tableName = "t_pianqu";
			ret = areaDao.queryPianquList(page);
			break;
		case 4:
			tableName = "t_hutong";
			ret = areaDao.queryHutongList(page);
			break;
		case 5:
			tableName = "t_managecompany";
			ret = areaDao.queryCompanyList(page);
			break;
		default:
			break;
		}
		int count = areaDao.queryAreaTotal(tableName);
		page.setTotal(count);
		return ret;
	}

	@Override
	public List<AreaBean> queryAreaList(int type) {
		List<AreaBean> ret = new ArrayList<AreaBean>();
		String tableName = "";
		switch (type) {
		case 1:
			ret = areaDao.queryAllState();
			break;
		case 2:
			ret = areaDao.queryAllStreet();
			break;
		case 3:
			ret = areaDao.queryAllPianqu();
			break;
		default:
			break;
		}
		return ret;
	}
}

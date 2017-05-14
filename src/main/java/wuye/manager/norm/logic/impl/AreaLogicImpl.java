package wuye.manager.norm.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.cache.Cache;
import wuye.manager.cache.CacheKeyConstant;
import wuye.manager.cache.CacheManager;
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
		
		Cache cache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_AREA+areaBean.getType());
		if(cache!=null){
			cache.setExpired(true);//设置缓存超时
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
		
		Cache cache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_AREA+areaBean.getType());
		if(cache!=null){
			cache.setExpired(true);//设置缓存超时
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
		
		Cache cache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_AREA+type);
		if(cache!=null){
			cache.setExpired(true);//设置缓存超时
		}
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
		Cache areaCache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_AREA+type);
		if(areaCache!=null&&!areaCache.isExpired()){
			return (List<AreaBean>) areaCache.getValue();
		}else{
			CacheManager.clearOnly(CacheKeyConstant.KEY_AREA+type);
		}
		
		List<AreaBean> ret = new ArrayList<AreaBean>();
		switch (type) {
		case 1:
			ret = areaDao.queryAllState();
			break;
		case 2:
			ret = areaDao.queryAllStreet(0);
			break;
		case 3:
			ret = areaDao.queryAllPianqu(0);
			break;
		case 4:
			ret = areaDao.queryAllHutong(0);
			break;
		case 5:
			ret = areaDao.queryAllCompany();
			break;
		default:
			break;
		}
		
		//缓存
		Cache cache = new Cache();
		cache.setKey(String.valueOf(type));
		cache.setValue(ret);
		CacheManager.putCache(CacheKeyConstant.KEY_AREA+type, cache);
		return ret;
	}
	
	public List<AreaBean> queryAreaByParentId(int type,int parentId){
		List<AreaBean> ret = new ArrayList<AreaBean>();
		switch (type) {
		case 2:
			ret = areaDao.queryAllStreet(parentId);
			break;
		case 3:
			ret = areaDao.queryAllPianqu(parentId);
			break;
		case 4:
			ret = areaDao.queryAllHutong(parentId);
			break;
		default:
			break;
		}
		return ret;
	}
	
	public String getAreaName(int id,int type){
		Cache areaCache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_AREA+type);
		boolean flag = true;
		List<AreaBean> areaList = null;
		if(areaCache==null){
			flag = false;
			areaList = queryAreaList(type);
		}else{
			areaList = (List<AreaBean>) areaCache.getValue();
		}
		
		for(int i=0;i<areaList.size();i++){
			AreaBean area = areaList.get(i);
			if(area.getId()==id){
				return area.getName();
			}
		}
		//缓存中没有--重新查询所有再次遍历
		if(flag){
			areaList = queryAreaList(type);
			for(int i=0;i<areaList.size();i++){
				AreaBean area = areaList.get(i);
				if(area.getId()==id){
					return area.getName();
				}
			}
		}
		return "";//已删除或不存在了
	}
}

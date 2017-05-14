package wuye.manager.norm.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.cache.Cache;
import wuye.manager.cache.CacheKeyConstant;
import wuye.manager.cache.CacheManager;
import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.norm.dao.NormDao;
import wuye.manager.norm.logic.NormLogic;
import wuye.manager.utils.PageUtil;

@Service("normLogic")
public class NormLogicImpl implements NormLogic {

	@Autowired
	private NormDao normDao;

	//--------------------------------考核级别设置--------------------------------
	
	public boolean addNormLevel(NormLevelBean normLevelBean){
		cacheTimeOut();
		return normDao.addNormLevel(normLevelBean);
	}
	
	public boolean updateNormLevel(NormLevelBean normLevelBean){
		cacheTimeOut();
		return normDao.updateNormLevel(normLevelBean);
	}
	
	public void deleteNormLevel(int id){
		normDao.deleteNormLevel(id);
		cacheTimeOut();
	}
	
	public void cacheTimeOut(){
		Cache cache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_AREA);
		if(cache!=null){
			cache.setExpired(true);//设置缓存超时
		}
	}
	
	public List<NormLevelBean> queryNormLevelList(){
		
		List<NormLevelBean> levelList = null;
		Cache cache = CacheManager.getCacheInfo(CacheKeyConstant.KEY_LEVEL);
		if(cache!=null){
			levelList = (List<NormLevelBean>) cache.getValue();
		}
		
		if(levelList==null){
			levelList = normDao.queryNormLevelList();
			cache = new Cache();
			cache.setKey(CacheKeyConstant.KEY_LEVEL);
			cache.setValue(levelList);
			CacheManager.putCache(CacheKeyConstant.KEY_LEVEL, cache);
		}
		return levelList;
	}
	
	
	@Override
	public String getNormLevelById(int id){
		List<NormLevelBean> levelList = queryNormLevelList();
		for(int i=0;i<levelList.size();i++){
			NormLevelBean bean = levelList.get(i);
			if(bean.getLevelNo() == id ){
				return bean.getLevelName();
			}
		}
		return "";
	}
	
	
	
	
	//--------------------------------考核类别设置--------------------------------
	
	public boolean addNormCategory(NormCategoryBean normCategoryBean){
		return normDao.addNormCategory(normCategoryBean);
	}
	
	public boolean updateNormCategory(NormCategoryBean normCategoryBean){
		return normDao.updateNormCategory(normCategoryBean);
	}
	
	public void deleteNormCategory(int id){
		normDao.deleteNormCategory(id);
	}
	
	public List<NormCategoryBean> queryNormCategoryList(PageUtil page){
		int count = normDao.getNormCategoryTotal();
		page.setTotal(count);
		return normDao.queryNormCategoryList(page);
	}
	
	public List<NormCategoryBean> queryNormCategoryList(){
		return normDao.queryNormCategoryList();
	}
	
	//--------------------------------考核项目设置--------------------------------
	
	public boolean addNormItem(NormItemBean normItemBean){
		return normDao.addNormItem(normItemBean);
	}
	
	public boolean updateNormItem(NormItemBean normItemBean){
		return normDao.updateNormItem(normItemBean);
	}
	
	public void deleteNormItem(int id){
		normDao.deleteNormItem(id);
	}
	
	public List<NormItemBean> queryNormItemList(PageUtil page){
		int count = normDao.getNormItemTotal();
		page.setTotal(count);
		return normDao.queryNormItemList(page);
	}
	
	

}

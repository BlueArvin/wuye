package wuye.manager.norm.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.norm.bean.NormBean;
import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.norm.dao.NormDao;
import wuye.manager.norm.logic.NormLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.PageUtil;

@Service("normLogic")
public class NormLogicImpl implements NormLogic {

	@Autowired
	private NormDao normDao;
	
	@Override
	public boolean addPianQu(NormBean NormBean) {
		// TODO Auto-generated method stub
		return normDao.addPianQu(NormBean);
	}

	@Override
	public List<NormBean> findAllPianQu(NormBean NormBean, PageUtil page) {
		// TODO Auto-generated method stub
		return normDao.findAllPianQu(NormBean, page);
	}
	
	public List<NormBean> findPianQuByFatherId(int fatherId){
		// TODO Auto-generated method stub
		return normDao.findPianQuByFatherId(fatherId);
	}

	@Override
	public NormBean getNormInfo(int id) {
		// TODO Auto-generated method stub
		return normDao.getNormInfo(id);
	}
	

	//--------------------------------考核级别设置--------------------------------
	
	public void addNormLevel(NormLevelBean normLevelBean){
		normDao.addNormLevel(normLevelBean);
	}
	
	public void updateNormLevel(NormLevelBean normLevelBean){
		normDao.updateNormLevel(normLevelBean);
	}
	
	public void deleteNormLevel(int id){
		normDao.deleteNormLevel(id);
	}
	
	public List<NormLevelBean> queryNormLevelList(){
		return normDao.queryNormLevelList();
	}
	
	
	//--------------------------------考核类别设置--------------------------------
	
	public void addNormCategory(NormCategoryBean normCategoryBean){
		normDao.addNormCategory(normCategoryBean);
	}
	
	public void updateNormCategory(NormCategoryBean normCategoryBean){
		normDao.updateNormCategory(normCategoryBean);
	}
	
	public void deleteNormCategory(int id){
		normDao.deleteNormCategory(id);
	}
	
	public List<NormCategoryBean> queryNormCategoryList(PageUtil page){
		int count = normDao.getNormCategoryTotal();
		page.setTotal(count);
		return normDao.queryNormCategoryList(page);
	}
	
	//--------------------------------考核项目设置--------------------------------
	
	public void addNormItem(NormItemBean normItemBean){
		normDao.addNormItem(normItemBean);
	}
	
	public void updateNormItem(NormItemBean normItemBean){
		normDao.updateNormItem(normItemBean);
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

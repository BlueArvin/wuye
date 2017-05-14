package wuye.manager.norm.logic;

import java.util.List;

import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.utils.PageUtil;

public interface NormLogic {
	
	//--------------------------------考核级别设置--------------------------------
	
	public boolean addNormLevel(NormLevelBean normLevelBean);
	
	public boolean updateNormLevel(NormLevelBean normLevelBean);
	
	public void deleteNormLevel(int id);
	
	public List<NormLevelBean> queryNormLevelList();
	
	public String getNormLevelById(int id);
	
	//--------------------------------考核类别设置--------------------------------
	
	public boolean addNormCategory(NormCategoryBean normCategoryBean);
	
	public boolean updateNormCategory(NormCategoryBean normCategoryBean);
	
	public void deleteNormCategory(int id);
	
	public List<NormCategoryBean> queryNormCategoryList(PageUtil page);
	
	public List<NormCategoryBean> queryNormCategoryList(int type);
	
	//--------------------------------考核项目设置--------------------------------
	
	public boolean addNormItem(NormItemBean normItemBean);
	
	public boolean updateNormItem(NormItemBean normItemBean);
	
	public void deleteNormItem(int id);
	
	public List<NormItemBean> queryNormItemList(PageUtil page);
	
	
	
}

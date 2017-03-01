package wuye.manager.norm.logic;

import java.util.List;

import wuye.manager.norm.bean.NormBean;
import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.utils.PageUtil;

public interface NormLogic {

	/**
	 * 添加区域
	 * @param userBean
	 * @return
	 */
	public boolean addPianQu(NormBean NormBean);
	
	/**
	 * 查询区域
	 * @param userBean
	 * @return
	 */
	public List<NormBean> findAllPianQu(NormBean NormBean,PageUtil page);
	
	public List<NormBean> findPianQuByFatherId(int fatherId);
	
	/**
	 * 查询区域信息
	 * @param id
	 * @return
	 */
	public NormBean getNormInfo(int id);
	
	
	
	//--------------------------------考核级别设置--------------------------------
	
	public void addNormLevel(NormLevelBean normLevelBean);
	
	public void updateNormLevel(NormLevelBean normLevelBean);
	
	public void deleteNormLevel(int id);
	
	public List<NormLevelBean> queryNormLevelList();
	
	
	//--------------------------------考核类别设置--------------------------------
	
	public void addNormCategory(NormCategoryBean normCategoryBean);
	
	public void updateNormCategory(NormCategoryBean normCategoryBean);
	
	public void deleteNormCategory(int id);
	
	public List<NormCategoryBean> queryNormCategoryList(PageUtil page);
	
	//--------------------------------考核项目设置--------------------------------
	
	public void addNormItem(NormItemBean normItemBean);
	
	public void updateNormItem(NormItemBean normItemBean);
	
	public void deleteNormItem(int id);
	
	public List<NormItemBean> queryNormItemList(PageUtil page);
	
	
	
}

package wuye.manager.norm.dao;

import java.util.List;

import wuye.manager.norm.bean.AreaBean;
import wuye.manager.utils.PageUtil;

public interface AreaDao {

	/**
	 * 添加城区
	 * @param stateBean
	 * @return
	 */
	public boolean addState(AreaBean stateBean);
	
	/**
	 * 添加街道
	 * @param stateBean
	 * @return
	 */
	public boolean addStreet(AreaBean streetBean);
	
	
	/**
	 * 添加片区
	 * @param stateBean
	 * @return
	 */
	public boolean addPianqu(AreaBean pianquBean);
	
	/**
	 * 添加胡同
	 * @param stateBean
	 * @return
	 */
	public boolean addHutong(AreaBean hutongBean);
	
	/**
	 * 添加物业
	 * @param stateBean
	 * @return
	 */
	public boolean addCompany(AreaBean companyBean);
	
	
	
	/**
	 * 查询城区列表
	 * @param stateBean
	 * @return
	 */
	public List<AreaBean> queryStateList(PageUtil page);
	
	/**
	 * 查询城区总数
	 * @param stateBean
	 * @return
	 */
	public int queryAreaTotal(String tableName);
	
	/**
	 * 查询街道列表
	 * @return
	 */
	public List<AreaBean> queryStreetList(PageUtil page);
	
	/**
	 * 查询街道列表
	 * @return
	 */
	public int queryStreetTotal();
	
	/**
	 * 查询片区列表
	 * @return
	 */
	public List<AreaBean> queryPianquList(PageUtil page);
	
	/**
	 * 查询片区列表
	 * @return
	 */
	public int queryPianquTotal();
	
	/**
	 * 查询胡同列表
	 * @return
	 */
	public List<AreaBean> queryHutongList(PageUtil page);
	
	/**
	 * 查询胡同数量
	 * @return
	 */
	public int queryHutongTotal();
	
	
	/**
	 * 查询物业列表
	 * @return
	 */
	public List<AreaBean> queryCompanyList(PageUtil page);
	
	/**
	 * 查询物业数量
	 * @return
	 */
	public int queryCompanyTotal();
	
	/**
	 * 修改城区
	 * @param stateBean
	 * @return
	 */
	public boolean updateState(AreaBean stateBean);
	
	/**
	 * 修改街道
	 * @param stateBean
	 * @return
	 */
	public boolean updateStreet(AreaBean streetBean);
	
	
	/**
	 * 修改片区
	 * @param stateBean
	 * @return
	 */
	public boolean updatePianqu(AreaBean pianquBean);
	
	/**
	 * 修改胡同
	 * @param stateBean
	 * @return
	 */
	public boolean updateHutong(AreaBean hutongBean);
	
	/**
	 * 修改物业
	 * @param stateBean
	 * @return
	 */
	public boolean updateCompany(AreaBean companyBean);
	
	
	
	/**
	 * 删除城区
	 * @param id
	 * @return
	 */
	public void addState(int id);
	
	/**
	 * 删除街道
	 * @param id
	 * @return
	 */
	public void addStreet(int id);
	
	/**
	 * 删除片区
	 * @param id
	 * @return
	 */
	public void addPianqu(int id);
	
	/**
	 * 删除胡同
	 * @param id
	 * @return
	 */
	public void addHutong(int id);
	
	/**
	 * 删除物业
	 * @param id
	 * @return
	 */
	public void addCompany(int id);
}

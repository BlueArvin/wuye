package wuye.manager.norm.logic;

import java.util.List;

import wuye.manager.norm.bean.AreaBean;
import wuye.manager.utils.PageUtil;

public interface AreaLogic {
	
	public boolean addArea(AreaBean areaBean);
	
	public boolean updateArea(AreaBean areaBean);
	
	public void deleteArea(int type,int id);
	
	public List<AreaBean> queryAreaList(int type,PageUtil page);
	
	public List<AreaBean> queryAreaList(int type);
	
}

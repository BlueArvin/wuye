package wuye.manager.assess.dao;

import java.util.List;

import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.utils.PageUtil;


public interface ManAssessDao {
	
	public List<ManAssessBean> queryList(ManAssessBean manAssessBean, PageUtil page);
	
	public int getTotal(ManAssessBean manAssessBean);
	
	public ManAssessBean getAssessInfo(ManAssessBean manAssessBean);
	
	public boolean updateAssessInfo(ManAssessBean manAssessBean);
	
	public boolean delAssessInfo(ManAssessBean manAssessBean);
}

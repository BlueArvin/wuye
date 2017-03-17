package wuye.manager.assess.logic;

import java.util.List;

import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.utils.PageUtil;

public interface ManAssessLogic {

	public List<ManAssessBean> queryList(ManAssessBean manAssessBean, PageUtil page);
	
	public ManAssessBean getAssessInfo(ManAssessBean manAssessBean);
	
	public boolean updateAssessInfo(ManAssessBean manAssessBean);
	
	public boolean delAssessInfo(ManAssessBean manAssessBean);
}

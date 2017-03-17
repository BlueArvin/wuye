package wuye.manager.assess.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.assess.dao.ManAssessDao;
import wuye.manager.assess.logic.ManAssessLogic;
import wuye.manager.utils.PageUtil;

@Service("manAssessLogic")
public class ManAssessLogicImpl implements ManAssessLogic{

	@Autowired
	private ManAssessDao manAssessDao;

	@Override
	public List<ManAssessBean> queryList(ManAssessBean manAssessBean,
			PageUtil page) {
		List<ManAssessBean> list = manAssessDao.queryList(manAssessBean, page);
		int total = manAssessDao.getTotal(manAssessBean);
		page.setTotal(total);
		return list;
	}

	@Override
	public ManAssessBean getAssessInfo(ManAssessBean manAssessBean) {
		return manAssessDao.getAssessInfo(manAssessBean);
	}

	@Override
	public boolean updateAssessInfo(ManAssessBean manAssessBean) {
		return manAssessDao.updateAssessInfo(manAssessBean);
	}

	@Override
	public boolean delAssessInfo(ManAssessBean manAssessBean) {
		return manAssessDao.delAssessInfo(manAssessBean);
	}
	
	
	
}

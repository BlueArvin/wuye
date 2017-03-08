package wuye.logic.impl;

import java.util.List;

import wuye.bean.AssessDataBean;
import wuye.dao.AssessDao;
import wuye.logic.AssessLogic;

public class AssessLogicImpl implements AssessLogic {

	private AssessDao assessDao;
	
	public AssessDao getAssessDao() {
		return assessDao;
	}

	public void setAssessDao(AssessDao assessDao) {
		this.assessDao = assessDao;
	}

	public int submit(AssessDataBean data) {
		
		return assessDao.submit(data);
	}

	@Override
	public List<String> getPoint() {
		return assessDao.getPoint();
	}

}

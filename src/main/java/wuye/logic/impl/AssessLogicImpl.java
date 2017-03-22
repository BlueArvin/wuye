package wuye.logic.impl;

import java.util.Date;
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


	public List<String> getPoint() {
		return assessDao.getPoint();
	}

	@Override
	public List<AssessDataBean> getDetailitem(Date dStart, Date dEnd,
			String areaid, String checkyewai, String checktitle, int page) {
		return assessDao.getDetailitem(dStart, dEnd, areaid, checkyewai, checktitle, page);
	}

}

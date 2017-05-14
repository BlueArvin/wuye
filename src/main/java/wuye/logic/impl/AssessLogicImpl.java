package wuye.logic.impl;

import java.util.Date;
import java.util.List;

import wuye.bean.AssessDataBean;
import wuye.bean.BadCheckList;
import wuye.bean.CheckDayItem;
import wuye.bean.PianquData;
import wuye.bean.PianquSortListBean;
import wuye.bean.WuyeSortListBean;
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


	public List<String> getPoint(int street,int pianqu,String time) {
		return assessDao.getPoint(street,pianqu,time);
	}
	
	public List<CheckDayItem> getCheckDayList(Date dStart, Date dEnd,
			String pianquid, String checkyenei, int page) {
		return assessDao.getCheckDayList(dStart, dEnd, pianquid, checkyenei, page);
	}

	@Override
	public List<AssessDataBean> getDetailitem(Date dStart,
			String pianquid, String checkyenei, int page) {
		return assessDao.getDetailitem(dStart, pianquid, checkyenei, page);
	}

	@Override
	public void doSumWeek() {
		assessDao.weekjisuanpianqu(); // 评定分数 这里，会有相关的分数压缩，也就是单项最大分
		assessDao.weekSumWuye();      // 物业的排名和处理
	}

	@Override
	public void doSumMonth() {
		assessDao.monthSumPianqu(1);
		assessDao.monthSumWuye();

	}

	@Override
	public List<PianquData> getPianquSortData(char type, String date, int jibie, String areaid) {
		return assessDao.getPianquSortData(type, date, jibie, areaid);
	}

	@Override
	public List<PianquSortListBean> getPianquSortList(char type, String date, String pianquid) {
		
		return assessDao.getPianquSortList(type, date, pianquid);
	}

	@Override
	public List<WuyeSortListBean> getWuyeSortList(char type, String date, String wuyeid) {
		return assessDao.getWuyeSortList(type, date, wuyeid);
	}

	@Override
	public BadCheckList getBadCheck(char type, String date) {
		return assessDao.getBadCheck(type, date);
	}
	
	
}

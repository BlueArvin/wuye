package wuye.logic;

import java.util.Date;
import java.util.List;

import wuye.bean.AssessDataBean;
import wuye.bean.BadCheckList;
import wuye.bean.CheckDayItem;
import wuye.bean.PianquData;
import wuye.bean.PianquSortListBean;
import wuye.bean.WuyeSortListBean;

public interface AssessLogic {

	public int submit(AssessDataBean data);
	
	public List<String> getPoint();
	
	public List<AssessDataBean> getDetailitem(Date dStart,
			String hutongid, String checkyenei, int page);
	
	public void doSumWeek();
	
	public void doSumMonth();
	
	public List<PianquData> getPianquSortData(char type, String date, int jibie, String areaid);
	
	public List<PianquSortListBean> getPianquSortList(char type, String date, String pianquid);
	
	public List<WuyeSortListBean> getWuyeSortList(char type, String date, String wuyeid);
	
	public List<CheckDayItem> getCheckDayList(Date dStart, Date dEnd,
			String pianquid, String checkyenei, int page);
	
	public BadCheckList getBadCheck(char type, String date);
}

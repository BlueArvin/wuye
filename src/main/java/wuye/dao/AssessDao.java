package wuye.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import wuye.bean.AssessDataBean;
import wuye.bean.BadCheckList;
import wuye.bean.CheckDayItem;
import wuye.bean.CheckTitle;
import wuye.bean.PianquData;
import wuye.bean.PianquRelationHead;
import wuye.bean.PianquSortListBean;
import wuye.bean.PicBean;
import wuye.bean.WuyeSortListBean;

public interface AssessDao {

	public int submit(AssessDataBean data);
	
	public List<String> getPoint(int street,int pianqu,String time);
	
	public List<AssessDataBean> getDetailitem(Date dStart,
			String hutongid, String checkyenei, int page) ;
	
	public int weekjisuanpianqu();
	
	public int weekSumWuye(int timeid);
	
//	public int weekSumPianqu(int yenei);
	
	public int monthSumWuye(int timeid);
	
	public int monthSumPianqu(int yenei);
	
	public List<PianquData> getPianquSortData(char type, String date, int yenei, String areaid);
	
	public List<PianquSortListBean> getPianquSortList(char type, String date, String areaid);
	
	public List<WuyeSortListBean> getWuyeSortList(char type, String date, String wuyeid);
	
	public List<CheckDayItem> getCheckDayList(Date dStart, Date dEnd,
			String pianquid, String yenei, int page);
	
	public BadCheckList getBadCheck(char type, String date);
	
	public List<PianquData> getPianquWeekData(int data);
	
	public List<CheckTitle> getCheckTitle(int type);  // 2, waiye, 1 neiye
	
	public List<PianquRelationHead> getRelationPainqu();
	
	public Map<String, Float> getScore(int date, int yenei);
	
	public int delAssess(String serialid);
	
	public List<PicBean> getPicList(int pianqu, int date);
	
	public Map<Integer, String> getAssessid();

	public int getPianquNum();

	public int delDoWeek(int dateId);
}

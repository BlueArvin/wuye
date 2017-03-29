package wuye.dao;

import java.util.Date;
import java.util.List;

import wuye.bean.AssessDataBean;
import wuye.bean.PianquData;

public interface AssessDao {

	public int submit(AssessDataBean data);
	
	public List<String> getPoint();
	
	public List<AssessDataBean> getDetailitem(Date dStart, Date dEnd,
			String areaid, String checkyewai, String checktitle, int page) ;
	
	public int weekjisuanpianqu();
	
	public int weekSumWuye(int yenei);
	
	public int weekSumPianqu(int yenei);
	
	public int monthSumWuye(int yenei);
	
	public int monthSumPianqu(int yenei);
	
	public List<PianquData> getPianquSortData(char type, String date, int yenei, String areaid);
}

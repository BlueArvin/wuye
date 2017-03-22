package wuye.dao;

import java.util.Date;
import java.util.List;

import wuye.bean.AssessDataBean;

public interface AssessDao {

	public int submit(AssessDataBean data);
	
	public List<String> getPoint();
	
	public List<AssessDataBean> getDetailitem(Date dStart, Date dEnd,
			String areaid, String checkyewai, String checktitle, int page) ;
}

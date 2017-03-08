package wuye.dao;

import java.util.List;

import wuye.bean.AssessDataBean;

public interface AssessDao {

	public int submit(AssessDataBean data);
	
	public List<String> getPoint();
}

package wuye.logic;

import java.util.List;

import wuye.bean.AssessDataBean;

public interface AssessLogic {

	public int submit(AssessDataBean data);
	
	public List<String> getPoint();
}

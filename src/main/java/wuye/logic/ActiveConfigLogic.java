package wuye.logic;

import wuye.bean.CheckConfigBean;
import wuye.bean.ConfigData;

public interface ActiveConfigLogic {
	
	// 获取常规数据
	public String getConfigVersion();
	
	public ConfigData getConfigData();
	
	// 获取评估数据
	public String getCheckConfigVersion();
	
	public CheckConfigBean getCheckConfigData();

}

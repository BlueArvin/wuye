package wuye.dao;

import wuye.bean.CheckConfigBean;
import wuye.bean.ConfigData;

public interface ConfigDao {

	public String getVersion();
	
	public ConfigData getConfigData();
	
	public String getCheckVersion();
	
	public CheckConfigBean getCheckConfigData();
}

package wuye.dao;

import wuye.bean.ConfigData;

public interface ConfigDao {

	public String getVersion();
	
	public ConfigData getConfigData();
}

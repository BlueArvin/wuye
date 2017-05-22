package wuye.logic.impl;

import java.util.concurrent.Executors;

import wuye.bean.CheckConfigBean;
import wuye.bean.ConfigData;
import wuye.dao.ConfigDao;
import wuye.logic.ActiveConfigLogic;

public class ActiveConfigImpl implements ActiveConfigLogic {
	

	public ConfigDao getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}

	public ConfigDao configDao;

	public String getConfigVersion() {
		return configDao.getVersion();
	}

	public ConfigData getConfigData() {
		return configDao.getConfigData();
	}
	
	
	

	public String getCheckConfigVersion() {
		// TODO Auto-generated method stub
		return configDao.getCheckVersion();
	}

	public CheckConfigBean getCheckConfigData() {
		// TODO Auto-generated method stub
		return configDao.getCheckConfigData();
	}

}

package wuye.logic.impl;

import wuye.bean.ConfigData;
import wuye.dao.ConfigDao;
import wuye.logic.ActiveConfigLogic;

public class ActiveConfigImpl implements ActiveConfigLogic {
	

	public ConfigDao configDao;

	public String getConfigVersion() {
		return configDao.getVersion();
	}

	public ConfigData getConfigData() {
		return configDao.getConfigData();
	}

}

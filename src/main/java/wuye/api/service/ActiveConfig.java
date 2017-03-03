package wuye.api.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.api.bean.RetBean;
import wuye.logic.ActiveConfigLogic;

/**
 * 客户端动态配置文件相关，为了优化客户端的体验
 * @author lujinfei
 *
 */
@Controller
@RequestMapping("/api/config")
public class ActiveConfig {
	
	private static Logger logger = Logger.getLogger("config");
	
	public ActiveConfigLogic getActiveConfigLogic() {
		return activeConfigLogic;
	}


	public void setActiveConfigLogic(ActiveConfigLogic activeConfigLogic) {
		this.activeConfigLogic = activeConfigLogic;
	}


	public ActiveConfigLogic activeConfigLogic;
	
	@RequestMapping("selection/lastversion")
	@ResponseBody
    public Object selectionLastVersion(HttpServletRequest request){
		logger.info("selectionLastVersion");
		return RetBean.ParseRet(activeConfigLogic.getConfigVersion());
    }
	

	@RequestMapping("selection/configdata")
	@ResponseBody
    public Object selectionConfigData(HttpServletRequest request){
		logger.info("selectionConfigData");
		return RetBean.ParseRet(activeConfigLogic.getConfigData());
    }
	
	
	@RequestMapping("check/lastversion")
	@ResponseBody
    public Object checkLastVersion(HttpServletRequest request){
		logger.info("checkLastVersion");
		return RetBean.ParseRet(activeConfigLogic.getCheckConfigVersion());
    }
	

	@RequestMapping("check/configdata")
	@ResponseBody
    public Object checkConfigdata(HttpServletRequest request){
		logger.info("checkConfigdata");
		return RetBean.ParseRet(activeConfigLogic.getCheckConfigData());
    }
}

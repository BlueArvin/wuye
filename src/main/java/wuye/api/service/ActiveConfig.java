package wuye.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.api.bean.RetBean;
import wuye.logic.ActiveConfigLogic;

@Controller
@RequestMapping("/api/config")
public class ActiveConfig {
	
	public ActiveConfigLogic getActiveConfigLogic() {
		return activeConfigLogic;
	}


	public void setActiveConfigLogic(ActiveConfigLogic activeConfigLogic) {
		this.activeConfigLogic = activeConfigLogic;
	}


	public ActiveConfigLogic activeConfigLogic;
	
	@RequestMapping("selection/lastversion")
	@ResponseBody
    public String selectionLastVersion(HttpServletRequest request){
		return RetBean.ParseRet(activeConfigLogic.getConfigVersion()).toJsonString();
    }
	

	@RequestMapping("selection/configdata")
	@ResponseBody
    public String selectionConfigData(HttpServletRequest request){
		return RetBean.ParseRet(activeConfigLogic.getConfigData()).toJsonString();
    }
	
	
	@RequestMapping("check/lastversion")
	@ResponseBody
    public String checkLastVersion(HttpServletRequest request){
		return RetBean.ParseRet(activeConfigLogic.getCheckConfigVersion()).toJsonString();
    }
	

	@RequestMapping("check/configdata")
	@ResponseBody
    public String checkConfigdata(HttpServletRequest request){
		return RetBean.ParseRet(activeConfigLogic.getCheckConfigData()).toJsonString();
    }
}

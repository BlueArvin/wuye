package wuye.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.logic.ActiveConfigLogic;

@Controller
@RequestMapping("/api/config")
public class ActiveConfig {
	
	public ActiveConfigLogic activeConfigLogic;
	
	@RequestMapping("selection/lastversion")
	@ResponseBody
    public String selectionLastVersion(HttpServletRequest request){
		return activeConfigLogic.getConfigVersion();
    }
	

	@RequestMapping("selection/configdata")
	@ResponseBody
    public String selectionConfigData(HttpServletRequest request){
		return JSON.toJSONString(activeConfigLogic.getConfigData());
    }
	
	
	@RequestMapping("check/lastversion")
	@ResponseBody
    public String checkLastVersion(HttpServletRequest request){
		return activeConfigLogic.getConfigVersion();
    }
	

	@RequestMapping("check/configdata")
	@ResponseBody
    public String checkConfigdata(HttpServletRequest request){
		return JSON.toJSONString(activeConfigLogic.getConfigData());
    }
}

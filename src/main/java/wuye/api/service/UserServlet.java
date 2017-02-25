package wuye.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.api.bean.RetBean;
import wuye.bean.LoginUserBean;
import wuye.logic.UserLogic;

/**
 * 用户操作相关 目前包括登录 修改密码
 * @author lujinfei
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserServlet {
	
	private static Logger logger = Logger.getLogger("user");
	
	public UserLogic getUserLogic() {
		return userLogic;
	}

	public void setUserLogic(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	private UserLogic userLogic;

	@RequestMapping("/login")
	@ResponseBody
    public String login(HttpServletRequest request){  
		String username = request.getParameter("username");  // 用户名
		String passwd = request.getParameter("passwd");      // 密码
		
		LoginUserBean loginUser = new LoginUserBean(username, passwd, 0);
		userLogic.UserLogin(loginUser); // 执行登录
		
		if(loginUser.getRet() == 0) {
			request.getSession().setAttribute("userid", loginUser.getUserid() + "");
		}
		
		logger.info("login:" + JSON.toJSONString(loginUser));
		
		return JSON.toJSONString(loginUser.toAPIresult());
    }
	
	@RequestMapping("/exist")
	@ResponseBody
    public String exist(HttpServletRequest request){  
		String username = request.getParameter("username");  // 用户名
		String cookie = request.getParameter("cookie");      // cookie
		
		LoginUserBean loginUser = new LoginUserBean(username, cookie, 1);
		userLogic.UserLogin(loginUser); // 执行登录
		
		if(loginUser.getRet() == 0) {
			request.getSession().setAttribute("userid", loginUser.getUserid() + "");
		}
		
		logger.info("exist:" + JSON.toJSONString(loginUser));
		
		return JSON.toJSONString(loginUser.toAPIresult());
    }
	
	@RequestMapping("/passwd")
	@ResponseBody
    public String passwd(HttpServletRequest request) {
		String username = request.getParameter("username");
		String pwd = request.getParameter("passwd");
		String newpwd  = request.getParameter("newpasswd");
		
		LoginUserBean loginUser = new LoginUserBean(username, pwd, 0);
		userLogic.UserChangepwd(loginUser, newpwd);
		
		logger.info("changepwd:"+ JSON.toJSONString(loginUser));
		
		return JSON.toJSONString(loginUser.toAPIresult());
    }
}

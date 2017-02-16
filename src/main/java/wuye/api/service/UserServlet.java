package wuye.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.api.bean.RetBean;
import wuye.bean.LoginUserBean;
import wuye.logic.UserLogic;

@Controller
@RequestMapping("/api")
public class UserServlet {
	
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
		String username = request.getParameter("username");  // �û���
		String passwd = request.getParameter("passwd");      // ����
		
		LoginUserBean loginUser = new LoginUserBean(username, passwd, 0);
		userLogic.UserLogin(loginUser); // 执行登录
		
		return JSON.toJSONString(loginUser.toAPIresult());
    }
	
	@RequestMapping("/passwd")
	@ResponseBody
    public RetBean passwd(HttpServletRequest request) {  
		return null;
    }
}

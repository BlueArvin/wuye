package wuye.api.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wuye.api.bean.RetBean;
import wuye.bean.LoginUserBean;
import wuye.logic.UserLogic;

@Controller
@RequestMapping("/api")
public class UserServlet {
	
	private UserLogic userLogic;

	@RequestMapping("/login")
	@ResponseBody
    public RetBean login(HttpServletRequest request){  
		String username = request.getParameter("username");  // ”√ªß√˚
		String passwd = request.getParameter("passwd");      // √‹¬Î
		
		LoginUserBean loginUser = new LoginUserBean(username, passwd, 0);
		
		return loginUser.toAPIresult();
    }  
	
	@RequestMapping("/passwd")
	@ResponseBody
    public RetBean passwd(HttpServletRequest request) {  
		return null;
    }
}

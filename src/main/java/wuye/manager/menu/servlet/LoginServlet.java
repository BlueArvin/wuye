package wuye.manager.menu.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wuye.bean.AssessDataBean;
import wuye.logic.AssessLogic;
import wuye.manager.menu.logic.LoginLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.utils.ManagerRetBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class LoginServlet {
private static Logger logger = Logger.getLogger("login");
	
	@Autowired
	private LoginLogic loginLogic;

	@RequestMapping("/main.aspx")
	public String toLogin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "loginName", required = false)String loginName,
			@RequestParam(value = "passwd", required = false)String passwd){
		
		
		System.out.println("---------------------"+loginName+"--------------"+passwd);
		
		return "redirect:/wuye/login.jsp";
	}

	@RequestMapping("/login.do")
	@ResponseBody
	public Object userLogin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "loginName", required = false)String loginName,
			@RequestParam(value = "passwd", required = false)String passwd){
		
		
		UserBean ub = loginLogic.checkUser(loginName,passwd);
		boolean flag = false;
		if(ub!=null){
			request.getSession().setAttribute("user", ub);
			flag = true;
		}
		
		System.out.println(loginName+"--------------"+passwd);
		
		
		ManagerRetBean retbean = new ManagerRetBean();
		if(!flag){
			retbean.setRet(-1);
			retbean.setMsg("用户名或密码错误");
		}
		return retbean;
//		return "redirect:/wuye/index.jsp";
//		return "index";
	}
}

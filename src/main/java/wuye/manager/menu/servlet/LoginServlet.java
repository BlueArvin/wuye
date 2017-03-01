package wuye.manager.menu.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wuye.manager.menu.bean.MenuBean;
import wuye.manager.menu.logic.LoginLogic;
import wuye.manager.menu.logic.MenuLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.user.logic.ManagerUserLogic;
import wuye.manager.utils.ManagerRetBean;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/manager")
public class LoginServlet {
private static Logger logger = Logger.getLogger("manager");
	
	@Autowired
	private LoginLogic loginLogic;
	
	@Autowired
	private MenuLogic menuLogic;
	
	@Autowired
	private ManagerUserLogic managerUserLogic;

	@RequestMapping("/login.aspx")
	@ResponseBody
	public Object userLogin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "loginName", required = false)String loginName,
			@RequestParam(value = "passwd", required = false)String passwd){
		
		
		UserBean ub = loginLogic.checkUser(loginName.trim(),passwd.trim());
		boolean flag = false;
		if(ub!=null){
			request.getSession().setAttribute("user", ub);
			flag = true;
		}
		
		System.out.println(loginName+"--------------"+passwd);
		
		ManagerRetBean retbean = new ManagerRetBean();
		if(flag){
			retbean.setRet(0);
			retbean.setMsg("登陆成功");
		}else{
			retbean.setRet(-1);
			retbean.setMsg("用户名或密码错误");
		}
		return retbean;
	}
	@RequestMapping("/logout.aspx")
	public Object userLogin(HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().removeAttribute("user");
		return "login";
	}
	@RequestMapping("/index.aspx")
	public String toIndex(HttpServletRequest request,HttpServletResponse response){
		
		//查询用户的菜单权限
		UserBean ub = (UserBean) request.getSession().getAttribute("user");
		
		List<String> list = ub.getRoleList();
		
		List<MenuBean> mbList = new ArrayList<MenuBean>();
		if(list!=null&&list.size()>0){
			mbList.addAll(menuLogic.findAllMenu(list));
		}
		System.out.println(JSONArray.toJSONString(mbList));
		
		request.setAttribute("menuList", mbList);
		return "index";
	}
	
	
	/**
	 * 跳转到updatePassword页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toUpdatePw.aspx")
	public String toUpdatePw(HttpServletRequest request,HttpServletResponse response){
		UserBean ub = (UserBean) request.getSession().getAttribute("user");
		request.setAttribute("loginName", ub.getCn());
		return "pass";
	}
	
	/**
	 * 更改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updatePw.aspx")
	@ResponseBody
	public Object updatePw(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "mpass", required = true)String passwd,
			@RequestParam(value = "newpass", required = true)String passwd1,
			@RequestParam(value = "renewpass", required = true)String passwd2){
		
		UserBean ub = (UserBean) request.getSession().getAttribute("user");
		
		ManagerRetBean retbean = new ManagerRetBean();
		UserBean userBean = loginLogic.checkUser(ub.getCn(), passwd);
		if(userBean==null){
			retbean.setRet(-1);
			retbean.setMsg("原生密码不正确，请重新输入");
			return retbean;
		}
		
		
		if(!passwd1.equals(passwd2)){
			retbean.setRet(-1);
			retbean.setMsg("密码不一致");
			return retbean;
		}
		
		boolean flag = managerUserLogic.updateUserPasswd(ub.getCn(), passwd1);
		
		if(flag){
			retbean.setRet(0);
			retbean.setMsg("修改成功");
		}else{
			retbean.setRet(-1);
			retbean.setMsg("修改失败，请重新尝试");
		}
		return retbean;
	}
	
}

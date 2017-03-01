package wuye.manager.user.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wuye.manager.menu.bean.MenuBean;
import wuye.manager.menu.logic.LoginLogic;
import wuye.manager.menu.logic.MenuLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.user.logic.ManagerUserLogic;
import wuye.manager.utils.ManagerRetBean;
import wuye.manager.utils.ManagerRetListBean;
import wuye.manager.utils.PageUtil;

import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/manager")
public class ManagerUserServlet {

private static Logger logger = Logger.getLogger("manager");
	
	@Autowired
	private LoginLogic loginLogic;
	
	@Autowired
	private ManagerUserLogic managerUserLogic;

	@RequestMapping("/addUser.aspx")
	@ResponseBody
	public Object addOrUpdateUserInfo(HttpServletRequest request,HttpServletResponse response,@Validated( { UserBean.class }) UserBean userBean
			,@RequestParam(value = "xmcheck", required = false)Integer xmcheck
			,@RequestParam(value = "jlquery", required = false)Integer jlquery
			,@RequestParam(value = "yhgl", required = false)Integer yhgl
			,@RequestParam(value = "bzsz", required = false)Integer bzsz
			,@RequestParam(value = "khxg", required = false)Integer khxg
			,@RequestParam(value = "sjfx", required = false)Integer sjfx){
		
		System.out.println("---"+userBean.toString());
		
		System.out.println(xmcheck+"---"+jlquery+"---"+yhgl+"--"+bzsz+"--"+khxg+"---"+sjfx);
		
		int role = 0;
		if(xmcheck!=null){
			role=role|2;
		}
		if(jlquery!=null){
			role=role|1;
		}
		userBean.setRole(role);
		String webRole="";
		if(yhgl!=null){
			webRole += yhgl+",";
		}
		if(bzsz!=null){
			webRole += bzsz+",";
		}
		if(khxg!=null){
			webRole += khxg+",";
		}
		if(sjfx!=null){
			webRole += sjfx+",";
		}
		userBean.setWebRole(webRole);
		
		if(userBean.getId()!=0){
			managerUserLogic.updateUserInfo(userBean);
			ManagerRetBean retbean = new ManagerRetBean();
			retbean.setRet(0);
			retbean.setMsg("修改成功");
			return retbean;
		}
		
		managerUserLogic.addUser(userBean);
		ManagerRetBean retbean = new ManagerRetBean();
		retbean.setRet(0);
		retbean.setMsg("添加成功");
		
		return retbean;
	}
	
	@RequestMapping("/toUpdateUser.aspx")
	public String toupdateUser(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "id", required = false)Integer id){
		
		System.out.println("id---------------------"+id);
		
		if(id==null){
			return "user_add";
		}
		
		UserBean ub = managerUserLogic.getUserInfo(id);
		request.setAttribute("userBean", ub);
		return "user_add";
	}
	
	@RequestMapping("/updateUser.aspx")
	@ResponseBody
	public Object updateUserInfo(HttpServletRequest request,HttpServletResponse response,@Validated( { UserBean.class }) UserBean userBean){
		
		System.out.println("---------------------"+userBean.toString());
		managerUserLogic.updateUserInfo(userBean);
		
		ManagerRetBean retbean = new ManagerRetBean();
		retbean.setRet(0);
		retbean.setMsg("修改成功");
		return retbean;
	}

	@RequestMapping("/userList.aspx")
	public String getUserList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "cn", required = false)String loginName,
			@RequestParam(value = "userName", required = false)String userName,
			@RequestParam(value = "pageNum", required = false)Integer pageNum){
		
		System.out.println(userName+"--------"+userName);
		
		UserBean ub = new UserBean();
		ub.setCn(loginName);
		ub.setUserName(userName);
		if(pageNum==null){
			pageNum=1;
		}
		PageUtil page = new PageUtil(pageNum);
		
		List<UserBean> list = managerUserLogic.findAllUser(ub, page);
		
		ManagerRetListBean<UserBean> rlb = new ManagerRetListBean<UserBean>();
		rlb.setRet(0);
		rlb.setPage(page);
		rlb.setList(list);
		
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		System.out.println("用户列表："+JSONObject.toJSONString(rlb));
		return "user_list";
	}
}

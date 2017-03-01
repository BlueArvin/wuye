package wuye.manager.norm.servlet;

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

import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.norm.logic.NormLogic;
import wuye.manager.user.bean.UserBean;
import wuye.manager.user.logic.ManagerUserLogic;
import wuye.manager.utils.ManagerRetBean;
import wuye.manager.utils.ManagerRetListBean;
import wuye.manager.utils.PageUtil;

import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/manager")
public class NormServlet {

private static Logger logger = Logger.getLogger("manager");
	
	@Autowired
	private NormLogic normLogic;

	/**
	 * to 考核级别页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toKhLevel.aspx")
	public String toKhLevel(HttpServletRequest request,HttpServletResponse response){
//		UserBean ub = (UserBean) request.getSession().getAttribute("user");
//		request.setAttribute("loginName", ub.getCn());
		
		List<NormLevelBean> nlb = normLogic.queryNormLevelList();
		request.setAttribute("list", nlb);
		return "kh_level";
	}
	
	/**
	 * to 考核类别页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toKhType.aspx")
	public String toKhType(HttpServletRequest request,HttpServletResponse response,
		@RequestParam(value = "pageNum", required = false)Integer pageNum){
//			UserBean ub = (UserBean) request.getSession().getAttribute("user");
//			request.setAttribute("loginName", ub.getCn());
			
		if(pageNum==null){
			pageNum=1;
		}
		PageUtil page = new PageUtil(pageNum);
		
		List<NormCategoryBean> nlb = normLogic.queryNormCategoryList(page);
		request.setAttribute("page", page);
		request.setAttribute("list", nlb);
		return "kh_type";
	}
	
	/**
	 * to 考核项目设置页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toItemSet.aspx")
	public String toItemSetting(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false)Integer pageNum){
//		UserBean ub = (UserBean) request.getSession().getAttribute("user");
//		request.setAttribute("loginName", ub.getCn());
		
		if(pageNum==null){
			pageNum=1;
		}
		PageUtil page = new PageUtil(pageNum);
		List<NormItemBean> nlb = normLogic.queryNormItemList(page);
		request.setAttribute("list", nlb);
		request.setAttribute("page", page);
		return "xm_set";
	}
	
	/**
	 * to 考核区域设置页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAreaSet.aspx")
	public String toAreaSetting(HttpServletRequest request,HttpServletResponse response){
//		UserBean ub = (UserBean) request.getSession().getAttribute("user");
//		request.setAttribute("loginName", ub.getCn());
		
		return "qy_set";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 添加或修改考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addKhLevel.aspx")
	public String addOrUpdateKhLevel(HttpServletRequest request,HttpServletResponse response
			,@Validated( { NormLevelBean.class }) NormLevelBean normLevelBean){
		System.out.println(normLevelBean.toString());
		if(normLevelBean.getLevelNo()!=0){//修改
			normLogic.updateNormLevel(normLevelBean);
		}else{//添加
			normLogic.addNormLevel(normLevelBean);
		}
		List<NormLevelBean> nlb = normLogic.queryNormLevelList();
		request.setAttribute("list", nlb);
		return "kh_level";
	}
	
	/**
	 * 删除考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delKhLevel.aspx")
	@ResponseBody
	public Object delKhLevel(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "id", required = false)Integer id){
		
		normLogic.deleteNormLevel(id);
		
		ManagerRetBean ret = new ManagerRetBean();
		ret.setRet(0);
		ret.setMsg("删除成功");
		return ret;
	}
	
	/**
	 * 添加或修改考核类别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addKhCate.aspx")
	public String addOrUpdateKhCate(HttpServletRequest request,HttpServletResponse response
			,@Validated( { NormCategoryBean.class }) NormCategoryBean normCategoryBean){
		System.out.println(normCategoryBean.toString());
		if(normCategoryBean.getCategoryNo()!=0){//修改
			normLogic.updateNormCategory(normCategoryBean);
		}else{//添加
			normLogic.addNormCategory(normCategoryBean);
		}
		List<NormCategoryBean> nlb = normLogic.queryNormCategoryList(new PageUtil(1));
		request.setAttribute("list", nlb);
		request.setAttribute("page", new PageUtil(1));
		return "kh_type";
	}
	
	/**
	 * 删除考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delKhCate.aspx")
	@ResponseBody
	public Object delKhCate(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "id", required = false)Integer id){
		
		normLogic.deleteNormCategory(id);
		
		ManagerRetBean ret = new ManagerRetBean();
		ret.setRet(0);
		ret.setMsg("删除成功");
		return ret;
	}
	
	
	/**
	 * 添加或修改考核类别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addKhItem.aspx")
	public String addOrUpdateKhItem(HttpServletRequest request,HttpServletResponse response
			,@Validated( { NormItemBean.class }) NormItemBean normItemBean){
		System.out.println(normItemBean.toString());
		if(normItemBean.getItemNo()!=0){//修改
			normLogic.updateNormItem(normItemBean);
		}else{//添加
			normLogic.addNormItem(normItemBean);
		}
		List<NormItemBean> nlb = normLogic.queryNormItemList(new PageUtil(1));
		request.setAttribute("list", nlb);
		request.setAttribute("page", new PageUtil(1));
		return "xm_set";
	}
	
	/**
	 * 删除考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delKhItem.aspx")
	@ResponseBody
	public Object delKhItem(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "id", required = false)Integer id){
		
		normLogic.deleteNormItem(id);
		
		ManagerRetBean ret = new ManagerRetBean();
		ret.setRet(0);
		ret.setMsg("删除成功");
		return ret;
	}
	
	/**
	 * 跳转到updatePassword页面
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping("/toUpdatePw.aspx")
	public String toUpdatePw(HttpServletRequest request,HttpServletResponse response){
		UserBean ub = (UserBean) request.getSession().getAttribute("user");
		request.setAttribute("loginName", ub.getCn());
		return "pass";
	}
	
	
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
	}*/
}

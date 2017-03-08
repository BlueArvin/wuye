package wuye.manager.norm.servlet;

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

import wuye.manager.norm.bean.NormCategoryBean;
import wuye.manager.norm.bean.NormItemBean;
import wuye.manager.norm.bean.NormLevelBean;
import wuye.manager.norm.logic.NormLogic;
import wuye.manager.utils.ManagerRetBean;
import wuye.manager.utils.PageUtil;


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
		page.setPageSize(5);
		List<NormItemBean> nlb = normLogic.queryNormItemList(page);
		List<NormLevelBean> levelList = normLogic.queryNormLevelList();
		
		List<NormCategoryBean> cateList = normLogic.queryNormCategoryList();
		
		request.setAttribute("list", nlb);
		request.setAttribute("levelList", levelList);
		request.setAttribute("cateList", cateList);
		request.setAttribute("page", page);
		return "xm_set";
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
		String msg = "";
		if(normLevelBean.getLevelNo()!=0){//修改
			if(normLogic.updateNormLevel(normLevelBean)){
				msg = "修改成功";
			}else{
				msg = "修改失败，请重新尝试";
			};
			
		}else{//添加
			if(normLogic.addNormLevel(normLevelBean)){
				msg = "添加成功";
			};
		}
		List<NormLevelBean> nlb = normLogic.queryNormLevelList();
		request.setAttribute("list", nlb);
		request.setAttribute("msg", msg);
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
		
		String msg="";
		if(normCategoryBean.getCategoryNo()!=0){//修改
			if(normLogic.updateNormCategory(normCategoryBean)){
				msg="修改成功";
			};
		}else{//添加
			if(normLogic.addNormCategory(normCategoryBean)){
				msg="添加成功";
			};
		}
		
		List<NormCategoryBean> nlb = normLogic.queryNormCategoryList(new PageUtil(1));
		request.setAttribute("list", nlb);
		request.setAttribute("page", new PageUtil(1));
		request.setAttribute("msg", msg);
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
		
		System.out.println("id===="+id);
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
			,@Validated( { NormItemBean.class }) NormItemBean normItemBean,
			@RequestParam("scoreValue") List<Integer> scoreValues,
			@RequestParam("scoreId") List<Integer> scoreIds){
		
		
		System.out.println(normItemBean);
		
		List<NormItemBean.NormScoreBean> list = new ArrayList<NormItemBean.NormScoreBean>();
		for(int i=0;i<scoreIds.size();i++ ){
			NormItemBean.NormScoreBean nsb = new NormItemBean.NormScoreBean();
			nsb.setLevelNo(scoreIds.get(i));
			nsb.setScore(scoreValues.get(i));
			list.add(nsb);
		}
		normItemBean.setScoreList(list);
		
		System.out.println("*********"+normItemBean.toString());
		String msg="";
		if(normItemBean.getItemNo()!=0){//修改
			normLogic.updateNormItem(normItemBean);
			msg="修改成功";
		}else{//添加
			normLogic.addNormItem(normItemBean);
			msg="添加成功";
		}
		
		PageUtil page = new PageUtil(1);
		page.setPageSize(5);
		List<NormItemBean> nlb = normLogic.queryNormItemList(page);
		List<NormLevelBean> levelList = normLogic.queryNormLevelList();
		List<NormCategoryBean> cateList = normLogic.queryNormCategoryList();
		
		request.setAttribute("list", nlb);
		request.setAttribute("levelList", levelList);
		request.setAttribute("cateList", cateList);
		request.setAttribute("page", page);
		request.setAttribute("msg", msg);
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

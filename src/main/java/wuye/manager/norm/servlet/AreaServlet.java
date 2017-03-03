package wuye.manager.norm.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wuye.manager.norm.bean.AreaBean;
import wuye.manager.norm.logic.AreaLogic;
import wuye.manager.utils.ManagerRetBean;
import wuye.manager.utils.PageUtil;


@Controller
@RequestMapping("/manager")
public class AreaServlet {

	@Autowired
	private AreaLogic areaLogic;
	
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
	 * to 考核区域设置页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAreaSetChild.aspx")
	public String toAreaSetting(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "type", required = true)Integer type
			,@RequestParam(value = "pageNum", required = true)Integer pageNum){
//		UserBean ub = (UserBean) request.getSession().getAttribute("user");
//		request.setAttribute("loginName", ub.getCn());
		
		
		if(type!=1&&type!=5){//城区和物业没有上级
			List<AreaBean> areaList = areaLogic.queryAreaList(type-1,null);
			request.setAttribute("parentList", areaList);
		}

		PageUtil page = new PageUtil(pageNum);
		List<AreaBean> areaList = areaLogic.queryAreaList(type,page);
		request.setAttribute("list", areaList);
		String retPage="qy_set_"+type;
		return retPage;
//		
//		switch (type) {
//		case 1://城区
//			
//			List<AreaBean> areaList = areaLogic.queryAreaList(type,page);
//			request.setAttribute("list", areaList);
//			retPage = "qy_set_1";
//			break;
//		case 2://街道
//			
//			areaLogic.queryAreaList(page);
//			request.setAttribute("list", areaList);
//			retPage = "qy_set_2";
//			break;
//		case 3://片区
//			
//			retPage = "qy_set_3";
//			break;
//		case 4://胡同
//			
//			retPage = "qy_set_4";
//			break;
//		case 5://物业
//			
//			retPage = "qy_set_5";
//			break;
//		default:
//			break;
//		}
//		
//		return retPage;
	}
	
	
	/**
	 * 添加或修改考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addArea.aspx")
	public String addOrUpdateKhLevel(HttpServletRequest request,HttpServletResponse response
			,@Validated( { AreaBean.class }) AreaBean areaBean){
		System.out.println(areaBean.toString());
		String msg = "";
		if(areaBean.getId()!=0){//修改
			if(areaLogic.updateArea(areaBean)){
				msg = "修改成功";
			}else{
				msg = "修改失败，请重新尝试";
			};
			
		}else{//添加
			if(areaLogic.addArea(areaBean)){
				msg = "添加成功";
			};
		}
		return "redirect:/manager/toAreaSetChild.aspx?type="+areaBean.getType()+"&pageNum=1&msg="+msg;
	}
	
	
	/**
	 * 添加或修改考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteArea.aspx")
	@ResponseBody
	public Object addOrUpdateKhLevel(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "type", required = true)Integer type
			,@RequestParam(value = "id", required = true)Integer id){


		areaLogic.deleteArea(type,id);
		
		ManagerRetBean ret = new ManagerRetBean();
		ret.setRet(0);
		ret.setMsg("删除成功");
		return ret;
	}
	
}

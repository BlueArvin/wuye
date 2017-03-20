package wuye.manager.assess.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wuye.manager.assess.bean.ManAssessBean;
import wuye.manager.assess.logic.ManAssessLogic;
import wuye.manager.utils.ManagerRetBean;
import wuye.manager.utils.PageUtil;

@Controller
@RequestMapping("/manager")
public class ManAssessServlet {

	@Autowired
	private ManAssessLogic manAssessLogic;
	
	
	
	/**
	 * to 问题修改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAssess.aspx")
	public String toAssess(HttpServletRequest request,HttpServletResponse response,
		@RequestParam(value = "pageNum", required = false)Integer pageNum
		,@Validated( { ManAssessBean.class }) ManAssessBean manAssessBean){
		if(pageNum==null){
			pageNum=1;
		}
		PageUtil page = new PageUtil(pageNum);
		List<ManAssessBean> nlb = manAssessLogic.queryList(manAssessBean,page);
		request.setAttribute("page", page);
		request.setAttribute("list", nlb);
		return "question";
	}
	
	
	/**
	 * 删除考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delAssess.aspx")
	@ResponseBody
	public Object delAssess(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "id", required = true)Integer id){
		
		ManAssessBean manAssessBean = new ManAssessBean();
		manAssessBean.setId(String.valueOf(id));
		manAssessLogic.delAssessInfo(manAssessBean);
		ManagerRetBean ret = new ManagerRetBean();
		ret.setRet(0);
		ret.setMsg("删除成功");
		return ret;
	}
	
	/**
	 * 删除考核级别
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateAssess.aspx")
	@ResponseBody
	public Object delAssess(HttpServletRequest request,HttpServletResponse response
			,@RequestParam(value = "id", required = true)Integer id
			,@RequestParam(value = "score", required = true)Integer score){
		
		ManAssessBean manAssessBean = new ManAssessBean();
		manAssessBean.setId(String.valueOf(id));
		manAssessBean.setScore(score);;
		boolean flag = manAssessLogic.updateAssessInfo(manAssessBean);
		ManagerRetBean ret = new ManagerRetBean();
		if(flag){
			ret.setRet(0);
			ret.setMsg("修改成功");
		}else{
			ret.setRet(0);
			ret.setMsg("修改失败");
		}
		return ret;
	}
}

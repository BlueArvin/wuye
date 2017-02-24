package wuye.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.api.bean.RetBean;
import wuye.bean.AssessDataBean;
import wuye.logic.AssessLogic;

/**
 * 考核数据相关
 * @author lujinfei
 *
 */
@Controller
@RequestMapping("/api/Assess")
public class AssessServlet {
	
	private AssessLogic assessLogic;

	public AssessLogic getAssessLogic() {
		return assessLogic;
	}

	public void setAssessLogic(AssessLogic assessLogic) {
		this.assessLogic = assessLogic;
	}



	@RequestMapping("submit")
	@ResponseBody
    public String submit(HttpServletRequest request){
		String sdd = request.getParameter("data");  // 街道id%检查id%物业id%扣分情况%时间字符串
		String pic1 = request.getParameter("pic1");      // 图片url
		String pic2 = request.getParameter("pic2");      // 图片url
		String pic3 = request.getParameter("pic3");      // 图片url
		
		String uid = (String)request.getSession().getAttribute("userid");
		if(uid == null) {   // 没有登录
			return JSON.toJSONString(new RetBean(2, "未登录"));
		}
		
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss"); 
		AssessDataBean data = new AssessDataBean();
		
		String[] temp = sdd.split("%");
		data.setStreetid(temp[0]);
		data.setAssessid(temp[1]);
		data.setWuyeid(Integer.parseInt(temp[2]));
		data.setUserid(Integer.parseInt(uid));
		data.setScore(Integer.parseInt(temp[3]));
		try {
			data.setTime(time.parse(temp[4]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setImg1(pic1);
		data.setImg2(pic2);
		data.setImg3(pic3);
		
		int ret = assessLogic.submit(data);
		String aseid = data.getAssessid();
		if(ret == 0) {
			RetBean jsonRet = new RetBean(0, "");
			jsonRet.setValue(aseid);
			return JSON.toJSONString(jsonRet);
		}else {
			return JSON.toJSONString(new RetBean(99, "操作失败"));
		}
    }
}

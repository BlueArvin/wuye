package wuye.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		String sdd = request.getParameter("data");  // 街道id%检查id%物业id%扣分项%扣分情况%时间字符串
		String pic1 = request.getParameter("pic1");      // 图片url
		String pic2 = request.getParameter("pic2");      // 图片url
		String pic3 = request.getParameter("pic3");      // 图片url
		
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss"); 
		AssessDataBean data = new AssessDataBean();
		
		String[] temp = sdd.split("%");
		data.setStreetid(Integer.parseInt(temp[0]));
		data.setAssessid(Integer.parseInt(temp[1]));
		data.setWuyeid(Integer.parseInt(temp[2]));
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
		
		
		return assessLogic.submit(data) + "";
    }
}

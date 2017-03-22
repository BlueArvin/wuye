package wuye.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import wuye.api.bean.RetBean;
import wuye.bean.AssessDataBean;
import wuye.logic.AssessLogic;

/**
 * 
 * @author lujinfei
 *
 */
@Controller
@RequestMapping("/api/Assess")
public class AssessServlet {
	
	private static Logger logger = Logger.getLogger("assess");
	
	private AssessLogic assessLogic;

	public AssessLogic getAssessLogic() {
		return assessLogic;
	}

	public void setAssessLogic(AssessLogic assessLogic) {
		this.assessLogic = assessLogic;
	}

	@RequestMapping("submit")
	@ResponseBody
    public Object submit(HttpServletRequest request){
		String serailID = request.getParameter("serailid");
		String sdd = request.getParameter("data");  // 
		String pic1 = request.getParameter("pic1");      // 
		String pic2 = request.getParameter("pic2");      // 
		String pic3 = request.getParameter("pic3");      // 
		String pic4 = request.getParameter("pic4");
		String loc = request.getParameter("loc");        // 
		String msg = request.getParameter("msg");
		
		String uid = (String)request.getSession().getAttribute("userid");
		if(uid == null) {   //
			return new RetBean(2, "未登录");
		}
		
		String right = (String)request.getSession().getAttribute("right");
		if(right == null || ((Integer.parseInt(right)& 0x01) == 0)) {   // 
			return new RetBean(3, "没有权限");
		}
		
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss"); 
		AssessDataBean data = new AssessDataBean();
		
		String[] temp = sdd.split("%");
		data.setAreaid(Integer.parseInt(temp[0].substring(1)));
		data.setStreetid(Integer.parseInt(temp[1].substring(1)));
		data.setPianquid(Integer.parseInt(temp[2].substring(1)));
		data.setHutongid(Integer.parseInt(temp[3].substring(1)));
		data.setWuyeid(Integer.parseInt(temp[4].substring(2)));
		data.setJibieid(Integer.parseInt(temp[5]));
		data.setYeneiid(Integer.parseInt(temp[6]));
		data.setAssessidtop(Integer.parseInt(temp[7].substring(2)));
		data.setAssessid(Integer.parseInt(temp[8].substring(2)));
		data.setScore(Integer.parseInt(temp[9]));
		data.setLoc(loc);
		
		data.setUserid(Integer.parseInt(uid));
		
		try {
			data.setTime(time.parse(temp[10]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setSerailID(serailID);
		data.setImg1(pic1);
		data.setImg2(pic2);
		data.setImg3(pic3);
		data.setImg4(pic4);
		data.setMsg(msg);
		
		int ret = assessLogic.submit(data);
		int aseid = data.getAssessid();
		
		logger.info("assess:" + ret + "," + JSON.toJSONString(data));
		
		if(ret == 0) {
			RetBean jsonRet = new RetBean(0, "");
			jsonRet.setValue(aseid);
			return jsonRet;
		}else {
			return new RetBean(99, "其他错误");
		}
    }
	
	@RequestMapping("point")
	@ResponseBody
    public Object point(HttpServletRequest request) {
		return assessLogic.getPoint();
	}
	
	@RequestMapping("allsort")
	@ResponseBody
    public Object allsort(HttpServletRequest request) {
		
		String start = request.getParameter("start");      // 
		String end = request.getParameter("end");      // 
		String areaid = request.getParameter("areaid");      // 
		String level = request.getParameter("level");
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dStart = df.parse(start);
			
			Date dEnd = df.parse(end);
			
			return assessLogic.getPoint();
		} catch(Exception e) {
			return new RetBean(2, "参数错误");
		}
	}
	
	@RequestMapping("itemsort")
	@ResponseBody
    public Object itemsort(HttpServletRequest request) {
		String start = request.getParameter("start");      // 
		String end = request.getParameter("end");      // 
		String checkitem = request.getParameter("checkitem");      // 
		String areaid = request.getParameter("areaid");
		String level = request.getParameter("level");
		
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dStart = df.parse(start);
			
			Date dEnd = df.parse(end);
			
			return assessLogic.getPoint();
		} catch(Exception e) {
			return new RetBean(2, "参数错误");
		}
	}
	
	@RequestMapping("squresort")
	@ResponseBody
    public Object squresort(HttpServletRequest request) {
		String start = request.getParameter("start");      // 
		String end = request.getParameter("end");      // 
		String areaid = request.getParameter("areaid");
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dStart = df.parse(start);
			
			Date dEnd = df.parse(end);
			
			return assessLogic.getPoint();
		} catch(Exception e) {
			return new RetBean(2, "参数错误");
		}
	}
	
	@RequestMapping("wuyesort")
	@ResponseBody
    public Object wuyesort(HttpServletRequest request) {
		String start = request.getParameter("start");      // 
		String end = request.getParameter("end");      // 
		String areaid = request.getParameter("areaid");
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dStart = df.parse(start);
			
			Date dEnd = df.parse(end);
			
			return assessLogic.getPoint();
		} catch(Exception e) {
			return new RetBean(2, "参数错误");
		}
	}
	
	@RequestMapping("detailitem")
	@ResponseBody
    public Object detailitem(HttpServletRequest request) {
		String start = request.getParameter("start");      // 
		String end = request.getParameter("end");      // 
		String areaid = request.getParameter("areaid");
		String checktitle = request.getParameter("checktitle");      // 
		String checksub = request.getParameter("checksub");
		
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date dStart = df.parse(start);
			
			Date dEnd = df.parse(end);
			
			return assessLogic.getPoint();
		} catch(Exception e) {
			return new RetBean(2, "参数错误");
		}
	}
}

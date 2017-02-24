package wuye.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AssessDataBean {

	public String id;
	public int userid;
	public Date time;
	public String streetid;    // 街道id，XXX@areaid
	public int areaid;
	public int wuyeid;
	public String assessid;    // 检查id小类 XXX@大类id
	public int assessidtop;    // 检查大类id
	public int score;
	public String img1;
	public String img2 = "";
	public String img3 = "";
	
	public AssessDataBean() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
    	id = "DF" + df.format(new Date());
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getStreetid() {
		return streetid;
	}
	public void setStreetid(String streetid) {
		this.streetid = streetid;
		String[] temp = streetid.split("@");
		this.areaid = Integer.parseInt(temp[1]);
	}
	public int getAreaid() {
		return areaid;
	}
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	public int getWuyeid() {
		return wuyeid;
	}
	public void setWuyeid(int wuyeid) {
		this.wuyeid = wuyeid;
	}
	public String getAssessid() {
		return assessid;
	}
	public void setAssessid(String assessid) {
		this.assessid = assessid;
		String[] temp = streetid.split("@");
		this.assessidtop = Integer.parseInt(temp[1]);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getId() {
		return id;
	}

	public int getAssessidtop() {
		return assessidtop;
	}
	
	
}

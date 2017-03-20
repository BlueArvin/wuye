package wuye.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AssessDataBean {

	public String id;
	public int userid;
	public Date time;
	public int streetid;    // 街道id，XXX@areaid
	public int areaid;
	public int pianquid;
	public int hutongid;
	public int wuyeid;
	public int jibieid;
	public int yeneiid;
	public int assessid;    // 检查id小类 XXX@大类id
	public int assessidtop;    // 检查大类id
	public int score;
	public String img1 = "";
	public String img2 = "";
	public String img3 = "";
	public String img4 = "";
	public String loc = "";
	public String msg = "";
	
	public String serailID = "";
	
	public String getSerailID() {
		return serailID;
	}

	public void setSerailID(String serailID) {
		this.serailID = serailID;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getPianquid() {
		return pianquid;
	}
	
	

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public void setPianquid(int pianquid) {
		this.pianquid = pianquid;
	}

	public int getHutongid() {
		return hutongid;
	}

	public void setHutongid(int hutongid) {
		this.hutongid = hutongid;
	}

	public int getJibieid() {
		return jibieid;
	}

	public void setJibieid(int jibieid) {
		this.jibieid = jibieid;
	}

	public int getYeneiid() {
		return yeneiid;
	}

	public void setYeneiid(int yeneiid) {
		this.yeneiid = yeneiid;
	}

	public void setStreetid(int streetid) {
		this.streetid = streetid;
	}

	public void setAssessidtop(int assessidtop) {
		this.assessidtop = assessidtop;
	}

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
	public int getStreetid() {
		return streetid;
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
	public int getAssessid() {
		return assessid;
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

	public void setAssessid(int assessid) {
		this.assessid = assessid;
	}

	public String getMsg() {
		try {
			return URLDecoder.decode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return msg;
		}
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}

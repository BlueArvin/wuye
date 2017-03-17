package wuye.manager.assess.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import wuye.bean.AssessDataBean;

public class ManAssessBean extends AssessDataBean {
	
	private String timeStr;
	private String userName;
	public String streetName;    // 街道id，XXX@areaid
	private String areaName;
	private String pianquName;
	private String hutongName;
	private String wuyeName;
	private String assessName;    // 检查id小类 XXX@大类id
	private String assesstopName;    // 检查大类id
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTimeStr() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		return df.format(time);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getPianquName() {
		return pianquName;
	}
	public void setPianquName(String pianquName) {
		this.pianquName = pianquName;
	}
	public String getHutongName() {
		return hutongName;
	}
	public void setHutongName(String hutongName) {
		this.hutongName = hutongName;
	}
	public String getWuyeName() {
		return wuyeName;
	}
	public void setWuyeName(String wuyeName) {
		this.wuyeName = wuyeName;
	}
	public String getAssessName() {
		return assessName;
	}
	public void setAssessName(String assessName) {
		this.assessName = assessName;
	}
	public String getAssesstopName() {
		return assesstopName;
	}
	public void setAssesstopName(String assesstopName) {
		this.assesstopName = assesstopName;
	}
	
}

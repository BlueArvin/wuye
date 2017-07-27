package wuye.bean;

import java.util.ArrayList;
import java.util.List;

public class PicBean {
	private int num = 0;
	private int assessid = 0;
	private double score  = 0;
	private String msg = "";
	private int hutong = 0;
	private String hutongName = "";
	
	public String getHutongName() {
		return hutongName;
	}

	public void setHutongName(String hutongName) {
		this.hutongName = hutongName;
	}

	private List<String> piclist; 

	public String getMsg() {
		return msg;
	}

	public int getHutong() {
		return hutong;
	}

	public void setHutong(int hutong) {
		this.hutong = hutong;
	}

	public void addMsg(String msg1) {
		if("".equals(msg.trim())) {
			msg += msg1.trim();
		} else {
			msg += "；" + msg1.trim();
		}
	}
	public PicBean() {
		piclist = new ArrayList<>(30);
	}

	public int getNum() {
		return num;
	}
	
	public void addCount() {
		num++;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAssessid() {
		return assessid;
	}

	public void setAssessid(int assessid) {
		this.assessid = assessid;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public void addScore(double score) {
		this.score += score;
	}

	public List<String> getPiclist() {
		return piclist;
	}

	public void setPiclist(List<String> piclist) {
		this.piclist = piclist;
	}
	
	public void addList(String dd) {
		this.piclist.add(dd);
	}
	
}

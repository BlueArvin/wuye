package wuye.bean;

public class CheckDayItem {
	private String day;
	private String hutong;
	private String hutongName;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHutong() {
		return hutong;
	}
	public void setHutong(int hutong) {
		this.hutong = "h" + hutong;
	}
	public String getHutongName() {
		return hutongName;
	}
	public void setHutongName(String hutongName) {
		this.hutongName = hutongName;
	}
	
	
}

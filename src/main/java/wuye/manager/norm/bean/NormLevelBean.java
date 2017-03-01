package wuye.manager.norm.bean;

public class NormLevelBean {

	private int levelNo;
	private String levelName;
	
	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	@Override
	public String toString() {
		return "NormLevelBean [levelNo=" + levelNo
				+ ", levelName=" + levelName + "]";
	}
	

}

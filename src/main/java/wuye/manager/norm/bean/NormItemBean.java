package wuye.manager.norm.bean;

import java.util.Map;

public class NormItemBean {

	private int categoryNo;//外键-Category
	private String categoryName;
	private int itemNo;//项目编号
	private String itemContent;//项目内容
	private Map<Integer,Double> levelMap;//考核级别比重
	
	private String scoreName;
	
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	public Map<Integer, Double> getLevelMap() {
		return levelMap;
	}
	public void setLevelMap(Map<Integer, Double> levelMap) {
		this.levelMap = levelMap;
	}
	
	public String getScoreName() {
		return scoreName;
	}
	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}
	@Override
	public String toString() {
		return "NormItemBean [categoryNo=" + categoryNo + ", categoryName="
				+ categoryName + ", itemNo=" + itemNo + ", itemContent="
				+ itemContent + ", levelMap=" + levelMap + "]";
	}

}

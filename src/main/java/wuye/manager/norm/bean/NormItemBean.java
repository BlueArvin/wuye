package wuye.manager.norm.bean;

import java.util.ArrayList;
import java.util.List;

public class NormItemBean {

	private int categoryNo;//外键-Category
	private String categoryName;
	private int itemNo;//项目编号
	private String itemContent;//项目内容
	private List<NormScoreBean> scoreList;
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
	
	public List<NormScoreBean> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<NormScoreBean> scoreList) {
		this.scoreList = scoreList;
	}
	
	public void addScoreBeanToList(NormScoreBean normScoreBean){
		if(scoreList==null){
			scoreList = new ArrayList<NormItemBean.NormScoreBean>();
		}
		scoreList.add(normScoreBean);
	}
	
	public String getScoreName() {
		String content = "";
		if(scoreList!=null&&scoreList.size()>0){
			for(int i=0;i<scoreList.size();i++){
				NormScoreBean nsb = scoreList.get(i);
				content += nsb.getLevelName()+":"+nsb.getScore()+"  ";
			}
		}
		return content;
	}
	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}
	@Override
	public String toString() {
		return "NormItemBean [categoryNo=" + categoryNo + ", categoryName="
				+ categoryName + ", itemNo=" + itemNo + ", itemContent="
				+ itemContent + "]";
	}
	

	public static class NormScoreBean{
		private int levelNo;
		private String levelName;
		private double score;
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
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
	}
	
}

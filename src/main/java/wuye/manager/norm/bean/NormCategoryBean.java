package wuye.manager.norm.bean;

public class NormCategoryBean {

	private int categoryNo;
	private String categoryName;
	private int business;//业务
	private String businessName; // （1:内业，2:外业）
	
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
	public int getBusiness() {
		return business;
	}
	public void setBusiness(int business) {
		this.business = business;
		if(business==1){
			this.businessName = "内业";
		}else if(business==2){
			this.businessName = "外业";
		}
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	@Override
	public String toString() {
		return "NormCategoryBean [categoryNo=" + categoryNo + ", categoryName="
				+ categoryName + ", business=" + business + ", businessName="
				+ businessName + "]";
	}
	
}

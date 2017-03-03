package wuye.manager.norm.bean;

public class AreaBean {
	
	private int id;
	private String name;
	private int parentId;
	private String parentName;
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}



/*

	//城区
	public static class StateBean{
		private int stateId;
		private String stateName;
		public int getStateId() {
			return stateId;
		}
		public void setStateId(int stateId) {
			this.stateId = stateId;
		}
		public String getStateName() {
			return stateName;
		}
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
	}
	
	//街道
	public static class StreetBean{
		private int stateId;
		private int streetId;
		private String streetName;
		public int getStateId() {
			return stateId;
		}
		public void setStateId(int stateId) {
			this.stateId = stateId;
		}
		public int getStreetId() {
			return streetId;
		}
		public void setStreetId(int streetId) {
			this.streetId = streetId;
		}
		public String getStreetName() {
			return streetName;
		}
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
	}
	
	//片区
	public static class PianquBean{
		private int streetId;
		private int pianquId;
		private String pianquName;
		public int getStreetId() {
			return streetId;
		}
		public void setStreetId(int streetId) {
			this.streetId = streetId;
		}
		public int getPianquId() {
			return pianquId;
		}
		public void setPianquId(int pianquId) {
			this.pianquId = pianquId;
		}
		public String getPianquName() {
			return pianquName;
		}
		public void setPianquName(String pianquName) {
			this.pianquName = pianquName;
		}
	}
	
	//胡同
	public static class HutongBean{
		private int pianquId;
		private int hutongId;
		private String hutongName;
		public int getPianquId() {
			return pianquId;
		}
		public void setPianquId(int pianquId) {
			this.pianquId = pianquId;
		}
		public int getHutongId() {
			return hutongId;
		}
		public void setHutongId(int hutongId) {
			this.hutongId = hutongId;
		}
		public String getHutongName() {
			return hutongName;
		}
		public void setHutongName(String hutongName) {
			this.hutongName = hutongName;
		}
	}
	
	//物业
	public static class CompanyBean{
		private int id;
		private String companyName;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
	}*/
	
}

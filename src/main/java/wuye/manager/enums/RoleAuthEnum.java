package wuye.manager.enums;


public class RoleAuthEnum {
	//区域-t_sys version
	public static enum AreaVersionEnum{
		addArea("/manager/addArea.aspx"),
		delArea("/manager/deleteArea.aspx");

		private String url;
		AreaVersionEnum(String url){
			this.url = url;
		}
		
		public String getUrl() {
			return url;
		}

		public static boolean include(String url) {  
	        for (AreaVersionEnum c : AreaVersionEnum.values()) {  
	            if (c.getUrl().equals(url)) {  
	                return true;
	            }
	        }  
	        return false;  
	    }
	}
	
	//考核-t_sys checkversion
	public static enum normVersionEnum{
		addNorm_Level("/manager/addKhLevel.aspx"),
		delNorm_Level("/manager/delKhLevel.aspx"),
		addNorm_Cate("/manager/addKhCate.aspx"),
		delNorm_Cate("/manager/delKh.aspx"),
		addNorm_Item("/manager/addKhItem.aspx"),
		delNorm_Item("/manager/addKhItem.aspx");
		
		private String url;
		normVersionEnum(String url){
			this.url = url;
		}
		
		public String getUrl() {
			return url;
		}

		public static boolean include(String url) {  
	        for (normVersionEnum c : normVersionEnum.values()) {  
	            if (c.getUrl().equals(url)) {  
	                return true;
	            }
	        }  
	        return false;  
	    }
	}

}

package wuye.manager.user.bean;

public enum UserRoleEnum {
	web_role_1("100000","用户管理"),
	web_role_2("200000","标准设置"),
	web_role_3("300000","考核修改"),
	web_role_4("400000","数据分析");

	private String key;
	private String name;
	
	UserRoleEnum(String key,String name){
		this.key = key;
		this.name = name;
	}
	
	public static String getName(String key) {  
        for (UserRoleEnum c : UserRoleEnum.values()) {  
            if (c.getKey().equals(key)) {  
                return c.name;
            }
        }  
        return null;  
    }
	
	public String getKey() {
		return key;
	}
	public String getName() {
		return name;
	}
	
	
}

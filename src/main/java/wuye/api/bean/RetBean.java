package wuye.api.bean;

public class RetBean {
	public int ret;
	public String msg;
	public Object value;

	public RetBean(int ret, String msg){
		this.ret = ret;
		this.msg = msg;
	}
	
	public void setValue(Object value){
		this.value = value;
	}
	
	
}

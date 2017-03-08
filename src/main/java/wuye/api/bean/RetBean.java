package wuye.api.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class RetBean {
	@JSONField(ordinal=1)
	public int ret;
	@JSONField(ordinal=2)
	public String msg;
	@JSONField(ordinal=3)
	public Object value = null;

	public RetBean(int ret, String msg){
		this.ret = ret;
		try {
			this.msg = URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			this.msg = "";
		}
	}
	
	public void setValue(Object value){
		this.value = value;
	}
	
	public String toJsonString() {
		return JSON.toJSONString(this);
	}
	
	public static RetBean ParseRet(Object ob) {
		RetBean ret = new RetBean(0, "");
		ret.setValue(ob);
		return ret;
	}
}

package wuye.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ConfigData {

	public String version = "";
	public List<Pair> state;
	public List<Pair> street;
	public List<Pair> check;
	public List<Pair> wuye;
	public List<Pair> pianqu;
	public List<Pair> hutong;
	
	
	public static class Pair {
		public String key;
		public String displayValue;
		public int property = 0;
		public String parent;
		
		public Pair(String key, String name, String parent) {
			this.key = key;
			this.parent = parent;
			try {
				this.displayValue = URLEncoder.encode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				this.displayValue = name;
			}
		}
		
		public Pair(String key, String name, int property) {
			this.key = key;
			this.property = property;
			try {
				this.displayValue = URLEncoder.encode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				this.displayValue = name;
			}
		}
		
		public Pair(String key, String name) {
			this.key = key;
			try {
				this.displayValue = URLEncoder.encode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				this.displayValue = name;
			}
		}
	}
}

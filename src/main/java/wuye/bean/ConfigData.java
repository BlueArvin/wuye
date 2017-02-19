package wuye.bean;

import java.util.List;

public class ConfigData {

	public String version = "";
	public List<Pair> state;
	public List<Pair> street;
	public List<Pair> check;
	public List<Pair> wuye;
	
	
	public static class Pair {
		public int key;
		public String displayValue;
		
		public Pair(int key, String name) {
			this.key = key;
			this.displayValue = name;
		}
	}
}

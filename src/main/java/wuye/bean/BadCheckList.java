package wuye.bean;

import java.util.ArrayList;
import java.util.List;

public class BadCheckList {
	
	public List<basic> basiclist;
	
	public BadCheckList() {
		basiclist = new ArrayList<>();
	}
	
	public static class basic {
		public int index;
		public int checksub;
		public String checksunName;
		public double baifenbi;
		
		public List<pianqu> pianqulist;
		
		public basic() {
			pianqulist = new ArrayList<>();
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getChecksub() {
			return checksub;
		}

		public void setChecksub(int checksub) {
			this.checksub = checksub;
		}

		public String getChecksunName() {
			return checksunName;
		}

		public void setChecksunName(String checksunName) {
			this.checksunName = checksunName;
		}

		public double getBaifenbi() {
			return baifenbi;
		}

		public void setBaifenbi(double baifenbi) {
			this.baifenbi = baifenbi;
		}

		public List<pianqu> getPianqulist() {
			return pianqulist;
		}

		public void setPianqulist(List<pianqu> pianqulist) {
			this.pianqulist = pianqulist;
		}
		
		
	}
	
	public static class pianqu {
		public int index;
		public int streetid;
		public String streetName;
		public int pianquid;
		public String pianquName;
		public double baifenbi;
		
		public double getBaifenbi() {
			return baifenbi;
		}
		public void setBaifenbi(double baifenbi) {
			this.baifenbi = baifenbi;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getStreetid() {
			return streetid;
		}
		public void setStreetid(int streetid) {
			this.streetid = streetid;
		}
		public String getStreetName() {
			return streetName;
		}
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
		public int getPianquid() {
			return pianquid;
		}
		public void setPianquid(int pianquid) {
			this.pianquid = pianquid;
		}
		public String getPianquName() {
			return pianquName;
		}
		public void setPianquName(String pianquName) {
			this.pianquName = pianquName;
		}
		
		
	}
}

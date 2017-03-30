package wuye.bean;

public class JisuanSortBean  implements Comparable {
	private int stateid = 0;
	private int streetid = 0;
	private int pianquid = 0;
	private double yenei = 0;
	private double yewai = 0;
	private double allscore = 0;
	
	private int paiming = 0;

	
	public void setall() {
		allscore = (100-yenei)*30/100 + ((100-yewai)*70/100);
	}
	
	
	
	public int getStateid() {
		return stateid;
	}



	public void setStateid(int stateid) {
		this.stateid = stateid;
	}



	public int getStreetid() {
		return streetid;
	}



	public void setStreetid(int streetid) {
		this.streetid = streetid;
	}



	public int getPianquid() {
		return pianquid;
	}

	public void setPianquid(int pianquid) {
		this.pianquid = pianquid;
	}

	public double getYenei() {
		return yenei;
	}

	public void addYenei(double yenei) {
		this.yenei += yenei;
	}
	
	public void addAll(double all) {
		this.yenei += all;
	}

	public double getYewai() {
		return yewai;
	}

	public void addYewai(double yewai) {
		this.yewai += yewai;
	}

	public double getAllscore() {
		return allscore;
	}

	public void setAllscore(double allscore) {
		this.allscore = allscore;
	}

	public int getPaiming() {
		return paiming;
	}

	public void setPaiming(int paiming) {
		this.paiming = paiming;
	}

	@Override
	public int compareTo(Object o) {
		JisuanSortBean bean = (JisuanSortBean)o;
		return ( (int)bean.allscore*10-(int)this.allscore*10 );
	}
	
	
}

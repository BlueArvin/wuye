package wuye.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 导出的excel用，包含子节点的关系
 * @author lujinfei
 *
 */
public class CheckTitle {
	private int id;
	private String name;
	private double score = 0.0;
	private List<CheckSub> sub = new ArrayList<>();
	
	public double getScore() {
		return score;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<CheckSub> getSub() {
		return sub;
	}

	public CheckTitle(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addSub(int id, String name, double score) {
		sub.add(new CheckSub(id, name, score));
		this.score += score;
	}
	
	public int getCount() {
		return sub.size();
	}
	
	public class CheckSub {
		private int id;
		private String name;
		private double score;
		
		public double getScore() {
			return score;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public CheckSub(int id, String name, double score) {
			this.id = id;
			this.name = name;
			this.score = score;
		}
	}
}

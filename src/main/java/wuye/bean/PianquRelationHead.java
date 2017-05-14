package wuye.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取片区数据，这里放在导出excel用
 * @author lujinfei
 *
 */
public class PianquRelationHead {
	
	private int id;
	private String name;
	private List<Pianqu> sub = new ArrayList<>();
	
	public PianquRelationHead(int id,String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addSub(int id,String name) {
		sub.add(new Pianqu(id, name));
	}
	
	public int getCount() {
		return sub.size();
	}

	
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public List<Pianqu> getSub() {
		return sub;
	}


	public class Pianqu {
		private int id;
		private String name;
		
		public Pianqu(int id,String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}
}

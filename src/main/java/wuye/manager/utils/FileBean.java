package wuye.manager.utils;

import java.util.List;

public class FileBean {
	private int type;// 0:文件夹 1：文件
	private String name;
	private List<FileBean> fbList;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FileBean> getFbList() {
		return fbList;
	}

	public void setFbList(List<FileBean> fbList) {
		this.fbList = fbList;
	}
}

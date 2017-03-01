package wuye.manager.utils;

import java.util.List;

public class ManagerRetListBean<T> extends ManagerRetBean{
	
	private List<T> list;
	private PageUtil page;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageUtil getPage() {
		return page;
	}
	public void setPage(PageUtil page) {
		this.page = page;
	}
}

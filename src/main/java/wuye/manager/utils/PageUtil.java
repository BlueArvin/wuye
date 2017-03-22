package wuye.manager.utils;

public class PageUtil {
	private int pageSize=10;
	private int page=1;
	private int startIndex;
	private int total;
	private int countPage;//共多少页
	
	
	public PageUtil(int page) {
		super();
		if(page<1){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public PageUtil(int page,int pageSize) {
		super();
		this.pageSize = pageSize;
		if(page<1){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getCountPage() {
		if(total%pageSize==0){
			return total/pageSize;
		}
		return (total/(pageSize))+1;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Integer getStartIndex() {
   		this.startIndex = (page - 1) * pageSize;
	    return this.startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	@Override
	public String toString() {
		return "PageUtil [pageSize=" + pageSize + ", page=" + page
				+ ", startIndex=" + startIndex + "]";
	}
}
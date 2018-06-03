package com.itplayer.core.base.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 把查询的结果封装到这个类
 * 
 */
public class PageResult<T> {
	// 数据List
	private List<T> rows = new ArrayList<T>();
	// 总记录数
	private int totalCount;
	// 总页数:计算出来
	private int totalPage;
	// 当前页码
	private int currentPage;
	// 一页显示的条数
	private int pageSize;

	public PageResult() {

	}

	public PageResult(int totalCount, int currentPage, int pageSize) {
		super();
		this.totalCount = totalCount;
		this.currentPage = currentPage < 1 ? 1 : currentPage;// 错误处理
		this.pageSize = pageSize < 1 ? 10 : pageSize;// 错误处理
		this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
		// 当前页数>总页数
		this.currentPage = this.currentPage > this.totalPage ? this.totalPage : this.currentPage;

	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageResult [rows.size=" + rows.size() + ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
	}
	
	
	// =================easyui-datagrid分页兼容=============================
//	public Integer getRows(){// 已有
//		
//	}
	
		public Integer getTotal(){
			return totalCount;
	  }

}

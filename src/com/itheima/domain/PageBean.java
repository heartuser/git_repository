package com.itheima.domain;

import java.util.List;

/**
 * 分页的JavaBean
 * 
 * @author Administrator
 */
public class PageBean<T> {

	// 当前页
	private int pageCode;

	// 总页数
	private int totalPage;

	// 总记录数
	private int totalCount;
	// 每页显示的记录条数
	private int pageSize = 2;
	// 每页显示的数据
	private List<T> beanList;

	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		if (pageCode <= 0){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	
	public int getTotalPage() {
		return this.totalPage;
	}

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		// 计算
		int totalPage = totalCount / pageSize;
		// 说明整除
		if (totalCount % pageSize == 0) {
			this.totalPage = totalPage;
		} else {
			this.totalPage = totalPage + 1;
		}
		if (pageCode > this.totalPage){
			pageCode = this.totalPage;
		}
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}

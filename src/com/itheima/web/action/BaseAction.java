package com.itheima.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	private static final long serialVersionUID = -100746590536492813L;
	protected Integer pageCode = 1;// 当前页，默认值为1
	protected Integer pageSize = 2;// 每页显示的记录数默认为2
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setVS(String Key, Object value){
		ActionContext.getContext().getValueStack().set(Key, value);
	}
	public void pushVS(Object value){
		ActionContext.getContext().getValueStack().push(value);
	}
}

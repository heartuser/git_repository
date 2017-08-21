package com.itheima.test;

import com.alibaba.fastjson.annotation.JSONField;

public class Person {
	
	private String pname;
	
	@JSONField(serialize=false) // 此注解表示该字段不会被解析成json数据
	private Role role;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}

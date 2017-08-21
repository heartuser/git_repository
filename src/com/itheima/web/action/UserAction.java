package com.itheima.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return this.user;
	}
	
	public String regist(){
		System.out.println(user);
		userService.save(user);
		return LOGIN;
	}
	
	/*
	 * 判断登录名是否存在
	 */
	public String checkName(){
		// 获取response
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("html/text;charset=utf-8");
		// 查询用户
		User u = userService.checkName(user.getUser_name());
		try {
			// 获取输出流
			PrintWriter writer = response.getWriter();
			if (u == null){
				// 用户不存在，可以注册
				writer.print("yes");
			}else{
				// 用户存在，不能注册
				writer.print("no");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String login(){
		User u = userService.login(user);
		if (u == null){
			// 用户名或密码错误
			return LOGIN;
		}else{
			// 成功登陆
			ServletActionContext.getRequest().getSession().setAttribute("existUser", u);
			return "loginOK";
		}
	}
	
	public String exit(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		return LOGIN;
	}

}

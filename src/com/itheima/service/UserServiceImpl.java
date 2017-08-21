package com.itheima.service;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User checkName(String user_code) {
		return userDao.checkName(user_code);
	}

	@Override
	public void save(User user) {
		String pwd = user.getUser_password();
		user.setUser_password(MD5Utils.md5(pwd)); // 给密码加密
		user.setUser_state("1");// 用户默认可用
		user.setUser_code("m0001");
		userDao.save(user);// 保存用户
	}

	@Override
	public User login(User user) {
		String pwd = user.getUser_password();
		user.setUser_password(MD5Utils.md5(pwd)); // 给密码加密
		user.setUser_state("1");// 用户默认可用
		return userDao.login(user);
	}
	
}

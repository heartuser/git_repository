package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao {

	User checkName(String user_name);

	void save(User user);

	User login(User user);
}

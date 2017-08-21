package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User checkName(String user_name) {
		List<User> uList = (List<User>) this.getHibernateTemplate().find("from User where user_name=?", user_name);
		if (uList != null && uList.size() > 0){
			return uList.get(0);
		}
		return null;
	}

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User login(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("user_name", user.getUser_name()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}

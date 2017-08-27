package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.PageBean;

public interface BaseDao<T> {
	public void save(T t);
	public void update(T t);
	public void delete(T t);
	public T findById(Long id);
	public List<T> findAll();
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}

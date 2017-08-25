package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

public interface CustomerService {
	public void save(Customer customer);
	public void update(Customer customer);
	public Customer getById(Long id);
	public List<Customer> findAll();
	public Customer findByLoad(String id);
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	public Customer finById(long cust_id);
	public void delete(Customer customer);
}

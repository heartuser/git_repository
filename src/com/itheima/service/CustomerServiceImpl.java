package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public Customer getById(Long id) {
		return customerDao.findById(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Customer> page = customerDao.findByPage(pageCode, pageSize, criteria);
		return page;
	}

	@Override
	public Customer finById(long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

}

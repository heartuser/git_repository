package com.itheima.dao;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.domain.Customer;

@Transactional
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {


}

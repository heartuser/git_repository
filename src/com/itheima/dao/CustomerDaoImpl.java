package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

@Transactional
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void save(Customer customer) {
		System.out.println("持久层：保存客户");
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@Override
	public Customer getById(Long id) {
		return this.getHibernateTemplate().get(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
//		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Customer findByLoad(String id) {
		return this.getHibernateTemplate().load(Customer.class, Long.parseLong(id));
	}

	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<Customer> page = new PageBean<Customer>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		// 查询总记录数
		criteria.setProjection(Projections.rowCount());// 设置查询记录数
		List<Number> countList = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (countList != null && countList.size() > 0){
			page.setTotalCount(countList.get(0).intValue());
		}
		criteria.setProjection(null);
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (page.getPageCode() - 1) * page.getPageSize(), page.getPageSize());
		page.setBeanList(beanList);
		return page;
	}

}

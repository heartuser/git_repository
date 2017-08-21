package com.itheima.web.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itheima.domain.Customer;
import com.itheima.domain.Dict;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private static final long serialVersionUID = 113695314694166436L;
	
	private Customer customer = new Customer();
	private CustomerService customerService;
	private Integer pageCode = 1;// 当前页，默认值为1
	private Integer pageSize = 2;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setPageCode(Integer pageCode) {
		if(pageCode == null || pageCode <= 0){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String save(){
		System.out.println("web:保存客户");
		customerService.save(customer);
		
		return NONE;
	}
	
	public String findByPage(){
		// 创建离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 拼条件(客户名)
		String cust_name = customer.getCust_name();
		if (cust_name != null && !cust_name.trim().isEmpty()){
			criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
		}
		
		// 拼条件(客户来源)
		Dict cust_source = customer.getCust_source();
		if (cust_source != null && !cust_source.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("cust_source.dict_id", cust_source.getDict_id()));
		}
		
		// 拼条件(客户级别)
		Dict cust_level = customer.getCust_level();
		if (cust_level != null && !cust_level.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("cust_level.dict_id", cust_level.getDict_id())); 
		}
		// 查询
		PageBean<Customer> page = customerService.findByPage(pageCode, pageSize, criteria);
		
		// 压栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", page);
		
		return "list";
	}
	

	@Override
	public Customer getModel() {
		return this.customer;
	}
}

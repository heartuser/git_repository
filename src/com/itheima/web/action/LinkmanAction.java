package com.itheima.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.service.LinkmanService;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman> {

	private Linkman linkman = new Linkman();
	private LinkmanService linkmanService;
	
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	@Override
	public Linkman getModel() {
		return this.linkman;
	}
	
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		String lkm_name = linkman.getLkm_name();
		if (lkm_name != null && !lkm_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%" + lkm_name + "%"));
		}
		
		Customer c = linkman.getCustomer();
		if (c != null && c.getCust_id() != null){
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		// 查询
		PageBean<Linkman> page = linkmanService.findByPage(pageCode, pageSize, criteria);
		// 压栈
		this.setVS("page", page);
		return "page";
	}

}

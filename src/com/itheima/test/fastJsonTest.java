package com.itheima.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.domain.Customer;

public class fastJsonTest {
	
	/*
	 * 演示fastjson的简单使用
	 */
	@Test
	public void run1(){
		Customer c = new Customer();
		c.setCust_id(1L);
		c.setCust_name("小明");
		c.setCust_phone("110");
		
		String jsonString = JSON.toJSONString(c);
		System.out.println(jsonString);// {"cust_id":1,"cust_name":"小明","cust_phone":"110"}
	}
	
	@Test
	public void run2(){
		List<Customer> list = new ArrayList<Customer>();
		Customer c1 = new Customer();
		c1.setCust_id(1L);
		c1.setCust_name("小明");
		c1.setCust_phone("110");
		
		Customer c2 = new Customer();
		c2.setCust_id(2L);
		c2.setCust_name("小红");
		c2.setCust_phone("120");
		
		list.add(c1);
		list.add(c2);
		
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);// [{"cust_id":1,"cust_name":"小明","cust_phone":"110"},{"cust_id":2,"cust_name":"小红","cust_phone":"120"}]
	}
	
	@Test
	public void run3(){
		List<Customer> list = new ArrayList<Customer>();
		Customer c1 = new Customer();
		c1.setCust_id(1L);
		c1.setCust_name("小明");
		c1.setCust_phone("110");
		
		list.add(c1);
		list.add(c1);
		
		String jsonString = JSON.toJSONString(list);
		System.out.println(jsonString);// [{"cust_id":1,"cust_name":"小明","cust_phone":"110"},{"$ref":"$[0]"}]
		String jsonString2 = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);// 禁止循环引用
		System.out.println(jsonString2);// [{"cust_id":1,"cust_name":"小明","cust_phone":"110"},{"cust_id":1,"cust_name":"小明","cust_phone":"110"}]
	}
	
	/*
	 * 两个类的字段存在相互引用，而在解析时禁止了循环引用，就会产生死循环，解决方法是使用注解 @JSONField(serialize=false)来取消某一方的引用转换成json
	 */
	@Test
	public void run4(){
		Person p = new Person();
		Role r = new Role();
		p.setPname("小明");
		r.setRname("管理员");
		p.setRole(r);
		r.setPerson(p);
		
		String jsonString = JSON.toJSONString(r, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonString);
	}
}

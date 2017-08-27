package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.itheima.domain.Customer;
import com.itheima.domain.Dict;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.utils.FastJsonUtil;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends BaseAction implements ModelDriven<Customer>{

	private static final long serialVersionUID = 113695314694166436L;
	
	private Customer customer = new Customer();
	private CustomerService customerService;
	
	/*
	 * 文件上传需要在action类中定义成员属性，命名是有规则的
	 * private File upload  表示要上传的文件
	 * private String uploadFileName  表示的是上传文件的名称
	 * private String uploadContentType 表示的是上传文件的MIME类型
	 * 并提供set方法
	 */
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/*
	 * 保存客户
	 */
	public String save() throws IOException{
		if (uploadFileName != null){
			// 选择了要上传的文件
			// 打印
			System.out.println("文件名称：" + uploadFileName);
			System.out.println("文件类型：" + uploadContentType);
			// 处理文件名
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			// 文件保存路径
			String filePath = "E:\\study\\apache-tomcat-8.5.16\\webapps\\upload\\";
			File file = new File(filePath + uuidName);
			// 上传
			FileUtils.copyFile(upload, file);
			// 保存文件路径
			customer.setFilePath(filePath + uuidName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}
	
	/*
	 * 删除客户
	 */
	public String delete(){
		// 获取要删除的客户id
		long cust_id = customer.getCust_id();
		// 查询出此客户
		Customer customer = customerService.finById(cust_id);
		// 删除客户
		customerService.delete(customer);
		// 获取用户文件
		File file = new File(customer.getFilePath());
		// 删除文件
		if (file.exists()){
			file.delete();
		}
		return "delete";
	}
	
	/*
	 * 分页查询客户列表
	 */
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
		System.out.println(page);
		// 压栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", page);
		
		return "list";
	}
	
	public String initAddUI(){
		
		return "initAdd";
	}
	
	public String initUpdate(){
		// 把需要编辑的客户查询出来
		customer = customerService.getById(customer.getCust_id());
		return "initUpdate";
	}
	
	public String update() throws IOException{
		if (uploadFileName != null && !uploadFileName.trim().isEmpty()){
			// 重新选择了文件，需要把原来的文件删除，并保存新的文件，和文件名
			String filePathOld = customer.getFilePath();
			if (filePathOld != null){
				// 删除旧文件
				File file = new File(filePathOld);
				if (file.exists()){
					file.delete();
				}
			}
			// 处理文件名
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			// 文件保存路径
			String filePath = "E:\\study\\apache-tomcat-8.5.16\\webapps\\upload\\";
			File file = new File(filePath + uuidName);
			// 上传
			FileUtils.copyFile(upload, file);
			// 保存文件路径
			customer.setFilePath(filePath + uuidName);
		}
		// 更新客户
		customerService.update(customer);
		return "saveSuccess";
	}
	
	public String findAll(){
		List<Customer> list = customerService.findAll();
		String jsonString = FastJsonUtil.toJSONString(list);
		FastJsonUtil.write_json(ServletActionContext.getResponse(), jsonString);
		return NONE;
	}
	

	@Override
	public Customer getModel() {
		return this.customer;
	}
}

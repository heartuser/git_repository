package com.itheima.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.Dict;
import com.itheima.service.DictService;
import com.itheima.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict> {
	
	private static final long serialVersionUID = -3087897119853766929L;
	private Dict dict = new Dict();
	private DictService dictService;

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	@Override
	public Dict getModel() {
		return this.dict;
	}
	
	public String findByCode(){
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
//		List<Dict> list = dictService.findByCode("006"); // 测试用
		String jsonString = FastJsonUtil.toJSONString(list);
		
		// 获取response
		HttpServletResponse response = ServletActionContext.getResponse();
		// 向浏览器输出
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}

}

package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IFunctionService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	@Autowired
	IFunctionService service;
	/**
	 * 功能：
	 * 1.查询所有权限数据；
	 * 2.一般用来添加权限时查询；
	 * @return
	 */
	public String listajax() {
		List<Function> list = service.findAll();
		this.java2Json(list, new String[] {"roles","parentFunction"});
		return NONE;
	}
	
	public String add() {
		service.save(model);
		return LIST;
	}
	
	public String pageQuery(){
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		service.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * 功能：
	 * 1.根据用户对象查询权限；
	 * @return
	 */
	public String findMenu() {
		/**
		 * 1.获取当前用户对象；
		 * 2.获取权限数据；
		 * 3.返回json格式数据；
		 */
		List<Function> list= service.findMenu();
		this.java2Json(list, new String[] {"roles","children"});
		
		return NONE;
		
	}
}

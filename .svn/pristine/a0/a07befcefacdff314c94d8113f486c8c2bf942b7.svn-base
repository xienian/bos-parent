package com.itheima.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IFunctionService;
import com.itheima.bos.service.IRoleService;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private String functionIds;
	
	
	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	@Autowired
	private IRoleService roleService;
	
	/**
	 * 功能：
	 * 1.保存角色及其配置的权限；
	 * @return
	 */
	public String add() {
		/**
		 * 1.将functionIds解析，获取角色想要拥有的权限id；
		 * 2.获取相应的权限对象；
		 * 3.将权限对象放入角色对象中；
		 * 4.保存；
		 */
		roleService.save(model,functionIds);
		return LIST;
	}
	
	/**
	 * 功能：
	 * 1.分页查询角色数据；
	 * @return
	 */
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"functions","users"});
		return NONE;
		
	}
	
	/*
	 * 功能：
	 * 1.查询所有角色；
	 * 2.返回json格式数据；
	 */
	public String listajax() {
	  	List<Role> list =	roleService.findAll();
	  	this.java2Json(list, new String[] {"functions","users"});
		return NONE;
		
	}


}

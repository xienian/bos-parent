package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.web.action.base.BaseAction;
import com.itheima.crm.ICustomerService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private static final String HOME = "home";
	//属性驱动
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	//用户要关联的角色id
	private String[] roleIds;
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	//用户对象，业务方法
	@Autowired
	private IUserService userService;
	
	//注入crm代理对象
	@Autowired
	private ICustomerService proxy;
	
	/**
	 * 登录验证
	 * @return
	 */
	public String login(){
		//获取校验码
		String validate = (String) ActionContext.getContext().getSession().get("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validate)) {
			//获取当前用户对象
			Subject subject = SecurityUtils.getSubject();
			//根据模型model中的用户名和密码，创建用户名密码令牌
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
			try{
				subject.login(token);
			}catch(Exception e){
				e.printStackTrace();
				return LOGIN;
			}
			//验证成功后
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		}else {
			//回显错误提示
			this.addActionError("您输入的验证码错误");
			return LOGIN;
		}
		
	}
	
	//用户注销，跳转到登录页面
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();;
		return LOGIN;
	}
	
	//修改密码
	public String editPassword() throws IOException{
		String f = "1";
		User user = BOSUtils.getLoginUser();
		userService.editPassword(user.getId(),model.getPassword());
		
		//ajax异步返回的数据
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}
	
	/*
	 * 功能：
	 * 1.查询所有角色；
	 * 2.返回json格式数据；
	 */
	public String listajax() {
	  	List<User> list =	userService.findAll();
	  	this.java2Json(list, new String[] {"functions","users"});
		return NONE;
		
	}
	/**
	 * 功能：
	 * 1.保存用户对象；
	 * @return
	 */
	public String add() {
		userService.save(model,roleIds);
		return LIST;
	}
	
	/**
	 * 功能：
	 * 1.分页查询用户信息；
	 * @return
	 */
	public String pageQuery() {
		userService.pageQuery(pageBean);
		this.java2Json(pageBean,new String[] {"noticebills","roles"});
		return NONE;
	}	
	
}

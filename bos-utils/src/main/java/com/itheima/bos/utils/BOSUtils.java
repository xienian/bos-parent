package com.itheima.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.itheima.bos.domain.User;

/**
 * bos物流小工具类
 * @author 10334
 *
 */
public class BOSUtils {
	
	//获取session对象
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	//获取会话对象中的登录对象
	public static  User getLoginUser(){
		return (User) getSession().getAttribute("loginUser");
	}

}

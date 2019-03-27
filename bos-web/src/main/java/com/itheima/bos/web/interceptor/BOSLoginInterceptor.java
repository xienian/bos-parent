package com.itheima.bos.web.interceptor;

import com.itheima.bos.domain.User;
import com.itheima.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BOSLoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2611337406984143626L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		/**
		 * 1.获取user对象； 2.如果有则放行执行，没有则跳转到登录页面；
		 */
		User user = BOSUtils.getLoginUser();
		if (user == null) {
			return "login";
		}
		return invocation.invoke();
	}

}

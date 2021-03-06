package com.itheima.bos.realm;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.bos.dao.IFunctionDao;
import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;

public class BOSRealm extends AuthorizingRealm{
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IFunctionDao functionDao;
	
	private static Logger logger = Logger.getLogger("com.itheima.bos.realm.BOSRealm");
	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("realm中的认证方法开始执行了。。。。");
		UsernamePasswordToken mytoken = (UsernamePasswordToken)token;
		String username = mytoken.getUsername();
		//根据用户名查询数据库中的密码
		User user = userDao.findUserByUserName(username);
		if(user == null){
			//用户名不存在
			logger.info("没有找到相应用户");
			return null;
		}
		//如果能查询到，再由框架比对数据库中查询到的密码和页面提交的密码是否一致
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/**
		 * 1.查询用户拥有的角色和权限；
		 * 2.根据权限授权；
		 */
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
			//超级管理员内置用户，查询所有权限数据
			list = functionDao.findByCriteria(detachedCriteria);
		}else{
			list = functionDao.findFunctionListByUserId(user.getId());
		}
		
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		//当还没有实现用户权限角色之间的功能的时候，手动添加一个可以访问取派员页面的功能
		info.addStringPermission("staff-list");
		return info;

	}
}

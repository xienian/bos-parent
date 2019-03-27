package com.itheima.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	  @Resource
	   public void setSessionFactory0(SessionFactory sessionFactory){  
	      super.setSessionFactory(sessionFactory);  
	   }
	//功能：根据用户名和口令获取用户对象，用来验证登录
	@Override
	public User findUserByUsernameAndPassword(String userame, String password) {
		
		String hql = "from User u where u.username=? and u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, userame,password);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public User findUserByUserName(String username) {
		String hql = "FROM User u WHERE u.username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}

package com.itheima.bos.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Staff;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {

	  @Resource
	   public void setSessionFactory0(SessionFactory sessionFactory){  
	      super.setSessionFactory(sessionFactory);  
	   }
	/**
	 * 功能：
	 * 1.还原某些取派员；
	 */
	@Override
	public void restore(String[] staffIds) {
		/*
		 *1. 
		 */
		String hql = "update Staff set deltag = 0 where id = ?";
		Query query = this.currentSession().createQuery(hql);
		for (String string : staffIds) {
			query.setString(0, string);
			query.executeUpdate();
		}
		
	}
}

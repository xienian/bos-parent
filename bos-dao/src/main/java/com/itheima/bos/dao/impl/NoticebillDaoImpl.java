package com.itheima.bos.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.INoticebillDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Noticebill;
@Repository
public class NoticebillDaoImpl extends BaseDaoImpl<Noticebill> implements INoticebillDao {
	@Resource
	 public void setSessionFactory0(SessionFactory sessionFactory){  
	    super.setSessionFactory(sessionFactory);  
	 }
	
}

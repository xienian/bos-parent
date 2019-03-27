package com.itheima.bos.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IWorkordermanageDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Workordermanage;
@Repository
public class WorkordermangeDaoImpl extends BaseDaoImpl<Workordermanage> implements IWorkordermanageDao {
	  @Resource
	   public void setSessionFactory0(SessionFactory sessionFactory){  
	      super.setSessionFactory(sessionFactory);  
	   }
}

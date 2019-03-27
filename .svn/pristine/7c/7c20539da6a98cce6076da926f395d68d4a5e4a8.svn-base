package com.itheima.bos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IDecidedzoneDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Decidedzone;

@Repository
public class DecidedzoneDaoImpl extends BaseDaoImpl<Decidedzone> implements IDecidedzoneDao {
	@Resource
	 public void setSessionFactory0(SessionFactory sessionFactory){  
	    super.setSessionFactory(sessionFactory);  
	 }
	/*
	 * 功能：
	 * 1.
	 * @see com.itheima.bos.dao.IDecidedzoneDao#findByIdAndStation(com.itheima.bos.domain.Decidedzone)
	 */
	@Override
	public List<Decidedzone> findByIdAndStation(Decidedzone model) {
		String hql = "FROM Decidedzone d where d.id = ?";
		List<Decidedzone> list = (List<Decidedzone>) this.getHibernateTemplate().find(hql, model.getId());
		return list;
	}
	


}

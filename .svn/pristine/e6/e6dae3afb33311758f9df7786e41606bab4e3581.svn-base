package com.itheima.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	/**
	 * 提取泛型实体类
	 */
	public Class<T> entityClass;
	
	public BaseDaoImpl(){
		Class<T> clazz = (Class<T>) this.getClass();
		ParameterizedType parameterizedType =  (ParameterizedType) clazz.getGenericSuperclass();
		Type[] types = parameterizedType.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
	}
	
	
	
	
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		T t = getHibernateTemplate().get(entityClass, id);
		return t;
	}

	@Override
	public List<T> findAll() {
		//测试，也不懂行不行
		return (List<T>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(entityClass));
	}



	/**
	 * 功能：
	 * 1.获取静态SQL语句并执行；
	 */
	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i=0;
		
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		
		query.executeUpdate();
		
	}



	/**
	 * 功能：
	 * 1.通用的分页查询方法
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		
		//查询总记录数
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list =  (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long count = list.get(0);
		pageBean.setTotal(count.intValue());
		
		//查询某页数据
		detachedCriteria.setProjection(null);//将指定封装变为select * ...
		//为了多表查询后在pagebean对象中的数据集合存的是映射对象而不是数组(放在这可能是时效性问题，例如放在baseaction中就没效果)
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = (currentPage-1)*pageSize;
		int maxResults = pageSize;
		List rows =  this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}



	/**
	 * 功能：
	 * 1.保存或更新实体，可以防止主键冲突；
	 */
	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		
	}



	/**
	 * 功能：
	 * 1.具有一定条件的查询方法；
	 */
	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return list;
	}

}

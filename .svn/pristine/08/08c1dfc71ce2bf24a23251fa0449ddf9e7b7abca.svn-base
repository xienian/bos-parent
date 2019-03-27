package com.itheima.bos.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.IRegionDao;
import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao{
	@Resource
	 public void setSessionFactory0(SessionFactory sessionFactory){  
	    super.setSessionFactory(sessionFactory);  
	 }
	/**
	 * 功能：
	 * 1.模糊查询区域对象;
	 */
	@Override
	public List<Region> findListByQ(String q) {
		String hql = "from Region r where r.shortcode like ?	or r.citycode like ?	or"
				+ " r.province like ?	or r.city like ? or r.district like ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}


}

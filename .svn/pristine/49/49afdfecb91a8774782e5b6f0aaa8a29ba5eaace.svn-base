package com.itheima.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareaDao;
	public void save(Subarea model) {
		subareaDao.save(model);
	}
	/**
	 * 功能：
	 * 1.分页查询分区数据
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
		
	}
	/**
	 * 功能：
	 * 1.返回所有的分区数据；
	 * 
	 */
	@Override
	public List<Subarea> findAll() {
		List<Subarea> list = null; 
		list = subareaDao.findAll();
		return list;
	}
	/**
	 * 功能：
	 * 1.批量保存或更新分区数据；
	 */
	@Override
	public void saveBatch(List<Subarea> subareas) {
		for (Subarea subarea : subareas) {
			subareaDao.saveOrUpdate(subarea);
		}
		
	}
	/**
	 * 功能：
	 * 1.查找没有关联定区的分区数据；
	 */
	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		List<Subarea> list = subareaDao.findByCriteria(detachedCriteria);
		return list;
	}
	
	/**
	 * 功能：
	 * 1
	 */
	@Override
	public List<Subarea> findListByDecidedzoneId(String id) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.eq("decidedzone.id", id));
		List<Subarea> list = subareaDao.findByCriteria(detachedCriteria);
		return list;
	}
	
	public List<Object> findSubareasGroupByProvince() {
		return subareaDao.findSubareasGroupByProvince();
	}
}

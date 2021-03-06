package com.itheima.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IDecidedzoneDao;
import com.itheima.bos.dao.ISubareaDao;
import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.IDecidedzoneService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	@Autowired
	private ISubareaDao subareaDao;
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	
	/**
	 * 功能：
	 * 1.保存定区对象；
	 */
	@Override
	public void save(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save(model);
		for (String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			if (subarea != null) {
				subarea.setDecidedzone(model);
			}
		}
		
	}
	/**
	 * 功能：
	 * 1.关联查询定区表和取派员表；
	 * 2.分页查询；
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
		
	}
	/**
	 * 功能：
	 * 1.根据id查询定区对象；
	 */
	@Override
	public Decidedzone findById(String id) {
		Decidedzone decidedzone = decidedzoneDao.findById(id);
		return decidedzone;
	}
	/*
	 * 功能：
	 * 1.根据定区id和取派员公司查询定区数据；
	 * @see com.itheima.bos.service.IDecidedzoneService#findByIdAndStation(com.itheima.bos.domain.Decidedzone)
	 */
	@Override
	public List<Decidedzone> findByIdAndStation(Decidedzone model) {
		List<Decidedzone> list = decidedzoneDao.findByIdAndStation(model);
		return list;
	}

}

package com.itheima.bos.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void save(Staff model) {
		staffDao.save(model);
		
	}
	/**
	 * 查询分页数据
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
		
	}
	
	//将指派员作废
	@Override
	public void deleteBatch(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] staffIds = ids.split(",");
			for (String id : staffIds) {
				staffDao.executeUpdate("staff.delete", id);
			}
		}

		
	}
	
	/**
	 * 功能：
	 * 1.根据id查找对象；
	 */
	@Override
	public Staff findById(Serializable id) {
		Staff staff = staffDao.findById(id);
		return staff;
	}
	
	/**
	 * 功能：
	 * 1.修改指派员数据；
	 */
	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
		
	}
	/**
	 * 功能：
	 * 1.查找没有作废的取派员；
	 */
	@Override
	public List<Staff> findByNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		List<Staff> list = staffDao.findByCriteria(detachedCriteria);
		return list;
	}
	
	/**
	 * 功能：
	 * 1.还原某些取派员；
	 */
	@Override
	public void restore(String[] staffIds) {
		staffDao.restore(staffIds);
		
	}
	/**
	 * 功能：
	 * 1.查询全部取派员
	 */
	@Override
	public List<Staff> findAll() {
		
		return staffDao.findAll();
	}

}

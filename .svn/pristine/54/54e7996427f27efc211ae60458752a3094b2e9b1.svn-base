package com.itheima.bos.service;

import java.io.Serializable;
import java.util.List;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;

public interface IStaffService {

	public void save(Staff model);

	public void pageQuery(PageBean pageBean);

	public void deleteBatch(String ids);

	public Staff findById(Serializable id);

	public void update(Staff staff);

	public List<Staff> findByNotDelete();

	public void restore(String[] staffIds);

	public List<Staff> findAll();

}

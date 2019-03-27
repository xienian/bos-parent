package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRegionDao;
import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	//dao对象
	@Autowired
	private IRegionDao regionDao;
	

	@Override
	public void saveOrUpdate(List<Region> regions) {
		
		
	}
	/**
	 * 功能：
	 * 1.批量更新区域对象
	 */
	@Override
	public void saveBatch(List<Region> regions) {
		for (Region region : regions) {
			regionDao.saveOrUpdate(region);
		}
		
	}
	/**
	 * 功能：
	 * 1.分页查询区域数据
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
		
	}
	/**
	 * 功能：
	 * 1.返回所有的区域数据；
	 */
	@Override
	public List<Region> findAll() {
		List<Region> list = regionDao.findAll();
		return list;
	}
	/**
	 * 功能
	 * 1.根据请求的查询值，返回模糊查询后的区域数据
	 */
	@Override
	public List<Region> findListByQ(String q) {
		List<Region> list = regionDao.findListByQ(q);
		return list;
	}
	
	/**
	 * 功能：
	 * 1.根据id查询区域对象；
	 */
	@Override
	public Region findById(String region_id) {
		return regionDao.findById(region_id);
	}

}

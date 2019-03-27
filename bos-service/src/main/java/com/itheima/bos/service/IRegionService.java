package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

public interface IRegionService {

	public void saveOrUpdate(List<Region> regions);

	public void saveBatch(List<Region> regions);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findListByQ(String q);

	public Region findById(String region_id);

}

package com.itheima.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IRoleDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.Role;
import com.itheima.bos.service.IRoleService;
import com.itheima.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public void save(Role model, String ids) {
		roleDao.save(model);
		if (StringUtils.isNotBlank(ids)) {
			String[] strings = ids.split(",");
			for(int i=0; i<strings.length; i++) {
				Function function = new Function();
				function.setId(strings[i]);
				model.getFunctions().add(function);
			}
		}

	}
	/*
	 * 功能：
	 * 1.分页查询角色对象；
	 * @see com.itheima.bos.service.IRoleService#pageQuery(com.itheima.bos.utils.PageBean)
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
		
	}
	/*
	 * 功能：
	 * 1.查询所有角色对象；
	 * @see com.itheima.bos.service.IRoleService#findAll()
	 */
	@Override
	public List<Role> findAll() {
		List<Role> list = roleDao.findAll();
		return list;
	}

}

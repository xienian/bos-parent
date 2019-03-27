package com.itheima.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IFunctionDao;
import com.itheima.bos.domain.Function;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IFunctionService;
import com.itheima.bos.utils.BOSUtils;
import com.itheima.bos.utils.PageBean;
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	@Autowired
	private IFunctionDao functionDao;

	@Override
	public List<Function> findAll() {
		List<Function> list = functionDao.findAll();
		return list;
	}
	/**
	 * 功能：
	 * 1.保存一项新权限；
	 */
	@Override
	public void save(Function model) {
		Function parantFunction = model.getParentFunction();
		if (parantFunction != null && parantFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}
		@Override
	public void pageQuery(PageBean pageBean) {
			functionDao.pageQuery(pageBean);
	}
		
		/*
		 *功能：
		 *1.查询当前用户所有权限；
		 * @see com.itheima.bos.service.IFunctionService#findMenu()
		 */
		@Override
		public List<Function> findMenu() {
			/**
			 * 1.如果用户是admin超级管理员，那么直接查询所有权限；
			 * 2.如果是其他的用户，那么根据用户id等查询；
			 */
			User user = BOSUtils.getLoginUser();
			String name = user.getUsername();
			List<Function> list = null;
			if (name.equals("admin")) {
				list = functionDao.findAllMenu();
			}else {
				list = functionDao.findMenuByUserId(user.getId());
			}
			return list;
		}


}

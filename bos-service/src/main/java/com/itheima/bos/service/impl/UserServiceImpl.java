package com.itheima.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.utils.PageBean;

@Service 
//事务注解，只能作用在public方法上
@Transactional
public class UserServiceImpl implements IUserService {
	
	//用户dao类
	private IUserDao userDao;
	
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	/**
	 * 功能：
	 * 1.通过用户对象模型，验证是否有相应用户，返回一个对象；
	 */
	public User login(User model) {
		//加密口令
		String password = MD5Utils.md5(model.getPassword());
		User user = userDao.findUserByUsernameAndPassword(model.getUsername(), password);
		return user;
	}

	/**
	 * 功能：
	 * 1.修改用户密码；
	 */
	@Override
	public void editPassword(String id, String password) {
		password = MD5Utils.md5(password);
		try {
			userDao.executeUpdate("user.editPassword", password,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}


	@Override
	public List<User> findAll() {
		return  userDao.findAll();
	}

	/**
	 * 功能：
	 * 1.保存用户对象；
	 * 2.关联想要关联的角色；
	 */
	@Override
	public void save(User model, String[] roleIds) {
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		model.setPassword(password);
		userDao.save(model);
		if (roleIds != null && roleIds.length>0) {
			for (String id : roleIds) {
				Role role = new Role(id);
				model.getRoles().add(role);
			}	
			
		}
		
	}

	/*
	 *功能：
	 *1.分页查询用户信息；
	 * @see com.itheima.bos.service.IUserService#pageQuery(com.itheima.bos.utils.PageBean)
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);	
	}

}

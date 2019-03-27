package com.itheima.bos.service;

import java.util.List;

import com.itheima.bos.domain.Role;
import com.itheima.bos.domain.User;
import com.itheima.bos.utils.PageBean;

public interface IUserService {

	public User login(User model);

	public void editPassword(String id, String password);

	public List<User> findAll();

	public void save(User model, String[] roleIds);

	public void pageQuery(PageBean pageBean);
	
}

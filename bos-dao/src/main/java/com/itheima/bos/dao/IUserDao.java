package com.itheima.bos.dao;

import com.itheima.bos.dao.base.IBaseDao;
import com.itheima.bos.domain.User;

public interface IUserDao extends IBaseDao<User>{
	//根据用户名和密码查找用户
	public User findUserByUsernameAndPassword(String userame,String password);

	public User findUserByUserName(String username);
}

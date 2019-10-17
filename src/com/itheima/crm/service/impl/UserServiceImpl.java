package com.itheima.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
@Transactional
public class UserServiceImpl implements UserService {
	//注入Dao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 业务层注册用户的方法
	 */
	public void regist(User user) throws Exception {
		System.out.println("用户管理的业务层的注册方法执行了。。。。");
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		//调用Dao
		userDao.save(user);
	}
	/**
	 * 	业务层的登录用户的方法
	 */
	public User login(User user) throws Exception {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		User existUser = userDao.login(user);
		return existUser;
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
	
	

}

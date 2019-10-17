package com.itheima.crm.service;

import java.util.List;

import com.itheima.crm.dao.BaseDao;
import com.itheima.crm.domain.User;

public interface UserService{

	void regist(User user)throws Exception ;

	User login(User user)throws Exception;

	List<User> findAll();

}

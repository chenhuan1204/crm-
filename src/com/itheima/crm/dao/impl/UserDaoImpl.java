package com.itheima.crm.dao.impl;



import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;



/**
 * 	用户管理的DAO实现类
 * @author chenhuan
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	//Dao中用户的登录方法
	public User login(User user) throws Exception {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());
		
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}

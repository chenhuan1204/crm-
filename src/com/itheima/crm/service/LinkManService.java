package com.itheima.crm.service;

import java.sql.SQLException;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.LinkMan;
import com.itheima.domain.PageBean;


public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long long1);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);
	
	
}

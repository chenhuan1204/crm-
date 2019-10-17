package com.itheima.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.domain.PageBean;

public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {


	public LinkMan findById(Long lkm_id) {
		return this.getHibernateTemplate().get(LinkMan.class,lkm_id);
	}
   
    

}

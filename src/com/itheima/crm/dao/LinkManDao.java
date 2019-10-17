package com.itheima.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.LinkMan;

public interface LinkManDao extends BaseDao<LinkMan> {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);


	LinkMan findById(Long lkm_id);


}

package com.itheima.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.SaleVisit;
import com.itheima.domain.PageBean;

/**
 * 客户拜访记录的业务层的接口
 * @author jt
 *
 */
public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}

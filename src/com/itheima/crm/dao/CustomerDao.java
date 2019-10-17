package com.itheima.crm.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.domain.PageBean;

public interface CustomerDao extends BaseDao<Customer>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);


	Customer findById(Customer customer);




	List<Customer> findAll();

}

package com.itheima.crm.service;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.domain.PageBean;

public interface CustomerService {
	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize);

	void save(Customer customer) throws IOException;

	Customer findById(Customer customer);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();

}

package com.itheima.crm.service.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.dao.impl.CustomerDaoImpl;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import com.itheima.domain.PageBean;
import com.opensymphony.xwork2.ModelDriven;
@Transactional
public class CustomerServiceImpl implements CustomerService {
	//注入客户的Dao
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		//封装当前页数；
		pageBean.setCurrentPage(currPage);
		//封装每页显示记录数
		pageBean.setPageSize(pageSize);
		 //封装总记录数
		 //调用Dao；
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalSize(totalCount);
		 //封装总页数
		Double tc = totalCount.doubleValue();
		Double num =  Math.ceil(tc/pageSize);//向上取整
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示数据的集合
		Integer begin = (currPage-1)*pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		System.out.println(pageBean.getList());
		return pageBean;
	}

	public void save(Customer customer) throws IOException {
		customerDao.save(customer);
	}

    /**
     * 通过cust_id查找customer
     */
    public Customer findById(Customer customer) {
     
        return customerDao.findById(customer);
    }
     
    /**
     * 业务层删除客户
     */
    public void delete(Customer customer) {
     
        customerDao.delete(customer);
    }

    /**
     * 修改用户
     */
    public void update(Customer customer) {
     
        customerDao.update(customer);
    }

	public List<Customer> findAll() {
		
		return customerDao.findAll();
	}


}

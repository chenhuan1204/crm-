package com.itheima.crm.dao.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.domain.PageBean;

public class CustomerDaoImpl extends BaseDaoImpl<Customer>  implements CustomerDao {
    /**
     * 通过cust_id查找customer
     */
    public Customer findById(Customer customer) {
        return this.getHibernateTemplate().get(Customer.class, customer.getCust_id());
    }

}
